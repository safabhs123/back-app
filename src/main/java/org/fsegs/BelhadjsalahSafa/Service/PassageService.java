package org.fsegs.BelhadjsalahSafa.Service;

/*import java.io.InputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

@Service
public class PassageService {

    public class PassageJournalier {
        private LocalDate date;
        private int nbOrdresPassage;
        private double montant;

        public PassageJournalier(LocalDate date, int nbOrdresPassage, double montant) {
            this.date = date;
            this.nbOrdresPassage = nbOrdresPassage;
            this.montant = montant;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getNbOrdresPassage() {
            return nbOrdresPassage;
        }

        public double getMontant() {
            return montant;
        }
    }

    public List<PassageJournalier> lireFichierTransporteur(InputStream fichierExcel, String nomFichier) throws Exception {
    	YearMonth moisAnnee = extraireMoisEtAnneeDepuisNomFichier(nomFichier);
    	if (moisAnnee == null) {
            throw new IllegalArgumentException("Impossible d'extraire mois et année depuis le nom du fichier : " + nomFichier);
        }

        Map<LocalDate, List<Row>> lignesParDate = new HashMap<>();
        Workbook workbook = WorkbookFactory.create(fichierExcel);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) continue;

            Cell cell = row.getCell(0);
            if (cell == null) continue;

            String jourTexte = getCellValueAsString(cell).trim();
            if (jourTexte.isEmpty() || !jourTexte.contains("-")) continue;

            try {
                int day = Integer.parseInt(jourTexte.split("-")[0]);
                LocalDate date = LocalDate.of(moisAnnee.getYear(), moisAnnee.getMonth(), day);
                lignesParDate.computeIfAbsent(date, d -> new ArrayList<>()).add(row);
            } catch (NumberFormatException | DateTimeException e) {
                continue;
            }
        }

        List<PassageJournalier> resultats = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Row>> entry : lignesParDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<Row> lignes = entry.getValue();

            double montantTotal = 0.0;
            int totalOrdresPassage = 0;

            for (Row row : lignes) {
                double trp = getCellValueAsDouble(row.getCell(15)); // Coût Trp.
                double trt = getCellValueAsDouble(row.getCell(16)); // Coût Trt.
                int ordPassage = getCellValueAsInt(row.getCell(5)); // Ord.Psg

                montantTotal += (trp + trt) * ordPassage;
                totalOrdresPassage += ordPassage;
            }

            resultats.add(new PassageJournalier(date, totalOrdresPassage, montantTotal));
        }

        workbook.close();
        return resultats;
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

    private double getCellValueAsDouble(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.NUMERIC)
                ? cell.getNumericCellValue()
                : 0.0;
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) return 0;

        try {
            if (cell.getCellType() == CellType.STRING) {
                String val = cell.getStringCellValue().toLowerCase();
                if (val.contains("1")) return 1;
                if (val.contains("2")) return 2;
                if (val.contains("3")) return 3;
                // etc.
                return 0;
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        } catch (Exception e) {
            return 0;
        }

        return 0;
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


}*/

import java.io.InputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

@Service
public class PassageService {

    public static  class PassageJournalier {
        private LocalDate date;
        private int nbOrdresPassage;
        private double montant;
        private double trp;
        private double trt;
        public String getNature() {
			return nature;
		}

		public void setNature(String nature) {
			this.nature = nature;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public void setNbOrdresPassage(int nbOrdresPassage) {
			this.nbOrdresPassage = nbOrdresPassage;
		}

		public void setMontant(double montant) {
			this.montant = montant;
		}

		private String  nature;


        public double getTrp() {
			return trp;
		}

		public void setTrp(double trp) {
			this.trp = trp;
		}

		public double getTrt() {
			return trt;
		}

		public void setTrt(double trt) {
			this.trt = trt;
		}

		

        public PassageJournalier(LocalDate date, int nbOrdresPassage, double montant, double trp, double trt,
				String nature) {
			super();
			this.date = date;
			this.nbOrdresPassage = nbOrdresPassage;
			this.montant = montant;
			this.trp = trp;
			this.trt = trt;
			this.nature = nature;
		}

		public LocalDate getDate() {
            return date;
        }

        public int getNbOrdresPassage() {
            return nbOrdresPassage;
        }

        public double getMontant() {
            return montant;
        }
    }

    public List<PassageJournalier> lireFichierTransporteur(InputStream fichierExcel, String nomFichier) throws Exception {
        YearMonth moisAnnee = extraireMoisEtAnneeDepuisNomFichier(nomFichier);
        if (moisAnnee == null) {
            throw new IllegalArgumentException("Impossible d'extraire mois et année depuis le nom du fichier : " + nomFichier);
        }

        Map<LocalDate, List<Row>> lignesParDate = new HashMap<>();
        Workbook workbook = WorkbookFactory.create(fichierExcel);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) continue;

            Cell cell = row.getCell(0);
            if (cell == null) continue;

            String jourTexte = getCellValueAsString(cell).trim();
            if (jourTexte.isEmpty() || !jourTexte.contains("-")) continue;

            try {
                int day = Integer.parseInt(jourTexte.split("-")[0]);
                LocalDate date = LocalDate.of(moisAnnee.getYear(), moisAnnee.getMonth(), day);
                lignesParDate.computeIfAbsent(date, d -> new ArrayList<>()).add(row);
            } catch (NumberFormatException | DateTimeException e) {
                continue;
            }
        }

        List<PassageJournalier> resultats = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Row>> entry : lignesParDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<Row> lignes = entry.getValue();

            double montantTotal = 0.0;
            int totalOrdresPassage = 0;

            double trpRef = 0.0;
            double trtRef = 0.0;
            String natureRef = "";

            for (Row row : lignes) {
                double trp = getCellValueAsDouble(row.getCell(15));
                double trt = getCellValueAsDouble(row.getCell(16));
                int ordPassage = getCellValueAsInt(row.getCell(5));
                String nature = getCellValueAsString(row.getCell(6));

                // mémoriser une valeur de référence pour le jour
                if (natureRef.isEmpty() && nature != null) natureRef = nature;
                if (trpRef == 0.0) trpRef = trp;
                if (trtRef == 0.0) trtRef = trt;

                montantTotal += (trp + trt) * ordPassage;
                totalOrdresPassage += ordPassage;
            }

            resultats.add(new PassageJournalier(date, totalOrdresPassage, montantTotal, trtRef, trpRef, natureRef));
        }

        workbook.close();
        return resultats;
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

    private double getCellValueAsDouble(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.NUMERIC)
                ? cell.getNumericCellValue()
                : 0.0;
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) return 0;

        try {
            if (cell.getCellType() == CellType.STRING) {
                String val = cell.getStringCellValue().toLowerCase();
                if (val.contains("1")) return 1;
                if (val.contains("2")) return 2;
                if (val.contains("3")) return 3;
                return 0;
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        } catch (Exception e) {
            return 0;
        }

        return 0;
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
}
