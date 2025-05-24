package org.fsegs.BelhadjsalahSafa.Service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BanqueService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Lecture du fichier Excel fourni par la banque
     */
    public List<PassageBanque> lireFichierExcelBanque(InputStream fichierExcel) throws Exception {
        Map<LocalDate, Integer> nbPassagesParDate = new HashMap<>();
        String agsaRef = null;
        String typeRef = null;
        String nompRef = null;
        try (Workbook workbook = WorkbookFactory.create(fichierExcel)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // ignorer l'en-tête

                Cell agsaCell = row.getCell(1);
                Cell dateCell = row.getCell(2);
                Cell typeCell = row.getCell(5);
                Cell countCell = row.getCell(6);
                Cell nompCell = row.getCell(4);

                if (agsaCell == null || dateCell == null || typeCell == null || countCell == null|| nompCell == null) {
                    continue; // ligne incomplète
                }

                String agsa = getCellStringValue(agsaCell);
                LocalDate date = getCellDateValue(dateCell);
                String type = getCellStringValue(typeCell);
                int count = (int) getCellNumericValue(countCell);
                String nomp = (String) getCellStringValue(nompCell);
                if (agsa != null && date != null && type != null) {
                    // garder une seule valeur d'agsa et type pour réutiliser à la fin
                    agsaRef = agsa;
                    typeRef = type;
                    nompRef=nomp;
                    // ajouter le count au total du jour
                    nbPassagesParDate.merge(date, count, Integer::sum);
                }
            }
        }

        List<PassageBanque> passages = new ArrayList<>();
        for (Map.Entry<LocalDate, Integer> entry : nbPassagesParDate.entrySet()) {
            passages.add(new PassageBanque(agsaRef, entry.getKey(), typeRef, entry.getValue(), nompRef));
        }

        return passages;
    }


    // Méthodes utilitaires

    private String getCellStringValue(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING); // forcer en string si nécessaire
        return cell.getStringCellValue().trim();
    }

    private double getCellNumericValue(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private LocalDate getCellDateValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }
        return null;
    }


    /**
     * Extraction directe depuis la base via SQL
     */
    public List<PassageBanque> getPassagesDepuisBase(LocalDate start, LocalDate end) {
        String sql = "SELECT agsa, dateh, lib3, COUNT(DISTINCT dateh) AS nombre_passages " +
                     "FROM (" +
                     "  SELECT agsa, dateh, lib3 FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope = '160' AND eta = 'VA' " +
                     "    AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') AND ( ? IS NULL OR agep = ? )" +
                     "  UNION ALL " +
                     "  SELECT agsa, dateh, lib3 FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope IN ('253','456') AND eta = 'VA' AND nat = 'VERESP'" +
                     "    AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') AND ( ? IS NULL OR age2 = ? )" +
                     "      AND ncp2 IN ('1017200000', '1017200200', '1017204100')" +
                     "  UNION ALL " +
                     "  SELECT agsa, dateh, lib3 FROM bkheve WHERE dateh >= ? AND dateh <= ? AND eta = 'VA' AND nat = 'VERESP'" +
                     "    AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') AND ( ? IS NULL OR age2 = ? )" +
                     "      AND ncp2 IN ('1017400000', '1017400200', '1017404100')" +
                     "  UNION ALL " +
                     "  SELECT agsa, dateh, lib3 FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope = '255' AND eta = 'VA' AND nat = 'RETESP'" +
                     "    AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') AND ( ? IS NULL OR age1 = ? )" +
                     "      AND ncp1 IN ('1017300000', '1017300200', '1017304100')" +
                     ") AS subquery " +
                     "GROUP BY agsa, dateh, lib3";

        Object[] params = new Object[] {
            Date.valueOf(start), Date.valueOf(end), null, null,
            Date.valueOf(start), Date.valueOf(end), null, null,
            Date.valueOf(start), Date.valueOf(end), null, null,
            Date.valueOf(start), Date.valueOf(end), null, null
        };

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new PassageBanque(
                rs.getString("agsa"),
                rs.getDate("dateh").toLocalDate(),
                rs.getString("lib3"),
                rs.getInt("nombre_passages"),
                rs.getString("nomp")
                
            )
        );
    }
}
