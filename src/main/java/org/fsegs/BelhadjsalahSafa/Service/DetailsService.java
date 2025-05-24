package org.fsegs.BelhadjsalahSafa.Service;

import org.apache.poi.ss.usermodel.*;
import org.fsegs.BelhadjsalahSafa.Entity.DetailsBanque;
import org.fsegs.BelhadjsalahSafa.Entity.DetailsTransporteur;
import org.fsegs.BelhadjsalahSafa.Entity.Nature;
import org.fsegs.BelhadjsalahSafa.Entity.ResultatPassage;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DetailsService {
	 private List<DetailsBanque> detailsBanqueList = new ArrayList<>();
	  

	    public void chargerDetailsBanque(InputStream excelFile) throws Exception {
	        this.detailsBanqueList = lireDetailsBanque(excelFile);
	    }

	    public List<DetailsBanque> getDetailsBanqueByDate(String date) {
	        return detailsBanqueList.stream()
	                .filter(d -> d.getDate().equals(date))
	                .collect(Collectors.toList());
	    }

	public List<DetailsBanque> lireDetailsBanque(InputStream excelFile) throws Exception {
	    List<DetailsBanque> details = new ArrayList<>();

	    try (Workbook workbook = WorkbookFactory.create(excelFile)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue;

	            String agsa = getString(row.getCell(1));
	            LocalDate date = getDate(row.getCell(2));
	            String nomp = getString(row.getCell(3));
	            String type = getString(row.getCell(5));
	            int nb = (int) getNumeric(row.getCell(6));

	            details.add(new DetailsBanque(
	            	    agsa,
	            	    nomp,
	            	    type,
	            	    nb,
	            	    date.toString()
	            	));

	        }
	    }
	    this.detailsBanqueList = details;
	    return details;
	}
    public static YearMonth extraireMoisEtAnneeDepuisNomFichier(String nomFichier) {
        Map<String, Month> moisMap = Map.ofEntries(
            Map.entry("jan", Month.JANUARY),
            Map.entry("fev", Month.FEBRUARY),
            Map.entry("fév", Month.FEBRUARY),
            Map.entry("mar", Month.MARCH),
            Map.entry("avr", Month.APRIL),
            Map.entry("mai", Month.MAY),
            Map.entry("jun", Month.JUNE),
            Map.entry("jui", Month.JULY),
            Map.entry("aou", Month.AUGUST),
            Map.entry("aoû", Month.AUGUST),
            Map.entry("sep", Month.SEPTEMBER),
            Map.entry("oct", Month.OCTOBER),
            Map.entry("nov", Month.NOVEMBER),
            Map.entry("dec", Month.DECEMBER),
            Map.entry("déc", Month.DECEMBER)
        );

        String lower = nomFichier.toLowerCase();

        for (String key : moisMap.keySet()) {
            if (lower.contains(key)) {
                Pattern p = Pattern.compile(key + "\\s*(\\d{4})");
                Matcher m = p.matcher(lower);
                if (m.find()) {
                    int annee = Integer.parseInt(m.group(1));
                    return YearMonth.of(annee, moisMap.get(key));
                }
            }
        }
        throw new IllegalArgumentException("Impossible d'extraire mois et année depuis le nom du fichier : " + nomFichier);
    }
    private List<DetailsTransporteur> detailsTransporteurList = new ArrayList<>();
    public void chargerDetailsTransporteur(InputStream excelFile, String fileName) throws Exception {
        this.detailsTransporteurList = lireDetailsTransport(excelFile, fileName);
        System.out.println("✅ Détails transporteur chargés avec succès. Taille : " + detailsTransporteurList.size());
    }


    public List<DetailsTransporteur> getDetailsTransportByDate(String date) {
        return detailsTransporteurList.stream()
                .filter(d -> d.getDate().toString().equals(date)) // "2025-02-21"
                .collect(Collectors.toList());
    }

    public List<DetailsTransporteur> lireDetailsTransport(InputStream inputStream, String fileName) throws Exception {
        List<DetailsTransporteur> passages = new ArrayList<>();
        Map<LocalDate, Integer> passagesParJour = new HashMap<>();  // <-- Ajout de la map ici

        YearMonth moisAnnee = extraireMoisEtAnneeDepuisNomFichier(fileName);
        if (moisAnnee == null) {
            throw new IllegalArgumentException("Impossible d'extraire mois et année depuis le nom du fichier : " + fileName);
        }

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) continue;

            Cell cell = row.getCell(0);
            if (cell == null) continue;

            String jourTexte = getCellValueAsString(cell).trim();
            if (jourTexte.isEmpty() || !jourTexte.contains("-")) continue;

            try {
                int day = Integer.parseInt(jourTexte.split("-")[0]);
                LocalDate date = LocalDate.of(moisAnnee.getYear(), moisAnnee.getMonthValue(), day);

                String agence = getStringFromCell(row.getCell(3)); // libellé agence
                String nature = getStringFromCell(row.getCell(6)); // nature (string brute)
                String ordPassage = getCellValueAsString(row.getCell(5)); // récupération ordPassage (string)
                double montant = getNumericFromCell(row.getCell(17));
                String resultat = getStringFromCell(row.getCell(4));
                String nbPassagesStr = getCellValueAsString(row.getCell(5));
                double trp = getNumericFromCell(row.getCell(15));  // colonne 16 = index 15
                double trt = getNumericFromCell(row.getCell(16));
                int nbPassagesLigne = 0;

                try {
                    nbPassagesLigne = Integer.parseInt(nbPassagesStr);
                } catch (NumberFormatException e) {
                    nbPassagesLigne = 0;
                }
                // colonne 17 = index 16

                double montantTotal = trp + trt;

                // Additionner le nbPassages pour ce jour
                passagesParJour.put(date, passagesParJour.getOrDefault(date, 0) + nbPassagesLigne);

                // Crée l'objet DetailsTransporteur avec les bonnes valeurs
                DetailsTransporteur detail = new DetailsTransporteur(nature, montant, ordPassage, date, agence, resultat,trt,trp);
                detail.setTrp(trp);
               detail.setTrt(trt);

                // Stocker ordPassage si le setter existe
                detail.setNbPassages(ordPassage);
                passages.add(detail);

            } catch (NumberFormatException | DateTimeException e) {
                // Ignore ligne si jour invalide
                continue;
            } catch (Exception e) {
                System.out.println("Erreur à la ligne " + row.getRowNum() + " : " + e.getMessage());
            }
        }

        workbook.close();
        this.detailsTransporteurList = passages;
        return passages;
    }
   

   





    private String getStringFromCell(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private double getNumericFromCell(Cell cell) {
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

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    private LocalDate getDateFromCell(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }
        return null;
    }
    private String getString(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private double getNumeric(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        try {
            return Double.parseDouble(cell.getStringCellValue().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private LocalDate getDate(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(cell)) return null;
        return cell.getLocalDateTimeCellValue().toLocalDate();
    }
  

  

  

} 
