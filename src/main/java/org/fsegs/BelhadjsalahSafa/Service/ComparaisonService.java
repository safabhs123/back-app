package org.fsegs.BelhadjsalahSafa.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour.DetailBanque;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour.DetailTransporteur;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonn;
import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
import org.springframework.stereotype.Service;


@Service
public class ComparaisonService {

    public static class RapportComparaison {
        private LocalDate date;
        private int nbPassagesBanque;
        private int nbPassagesTransporteur;
        private boolean equal;
        private double montantTransporteur;
        public RapportComparaison() {
        }

        public RapportComparaison(LocalDate date, int nbPassagesBanque, int nbPassagesTransporteur, boolean equal, double montantTransporteur) {
            this.date = date;
            this.nbPassagesBanque = nbPassagesBanque;
            this.nbPassagesTransporteur = nbPassagesTransporteur;
            this.equal = equal;
            this.montantTransporteur = montantTransporteur;
        }

        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }

        public int getNbPassagesBanque() { return nbPassagesBanque; }
        public void setNbPassagesBanque(int nbPassagesBanque) { this.nbPassagesBanque = nbPassagesBanque; }

        public int getNbPassagesTransporteur() { return nbPassagesTransporteur; }
        public void setNbPassagesTransporteur(int nbPassagesTransporteur) { this.nbPassagesTransporteur = nbPassagesTransporteur; }

        public boolean isEqual() { return equal; }
        public void setEqual(boolean equal) { this.equal = equal; }

        public double getMontantTransporteur() { return montantTransporteur; }
        public void setMontantTransporteur(double montantTransporteur) { this.montantTransporteur = montantTransporteur; }

        @Override
        public String toString() {
            return "RapportComparaison{" +
                    "date=" + date +
                    ", nbPassagesBanque=" + nbPassagesBanque +
                    ", nbPassagesTransporteur=" + nbPassagesTransporteur +
                    ", equal=" + equal +
                    ", montantTransporteur=" + montantTransporteur +
                    '}';
        }
    }
    // Déclaration du repository en attribut
  /*  private final RapportComparaisonn rapportRepo;
    public ComparaisonService(RapportComparaisonn rapportRepo) {
        this.rapportRepo = rapportRepo;
    }

    public void saveComparaison(List<RapportComparaisonParJour> rapports) {
        List<RapportComparaison> entities = rapports.stream()
            .map(dto -> {
                RapportComparaison entity = new RapportComparaison();
                entity.setDate(dto.getDate());
                entity.setNbPassagesBanque(dto.getNbPassagesBanque());
                entity.setNbPassagesTransporteur(dto.getNbPassagesTransporteur());
               // entity.setMontantBanque(dto.getMontantBanque());
                entity.setMontantTransporteur(dto.getMontantTransporteur());
                return entity;
            })
            .toList();

      //  rapportRepo.saveAll(entities);
    }*/

    public List<RapportComparaisonParJour> comparer(List<PassageBanque> passagesBanque, List<PassageJournalier> passagesTransporteur, int tolerance) {
        Map<LocalDate, Integer> banqueParDate = new HashMap<>();
        Map<LocalDate, PassageBanque> detailsBanqueMap = new HashMap<>();
        Map<LocalDate, PassageJournalier> detailsTransporteurMap = new HashMap<>();

        for (PassageBanque pb : passagesBanque) {
            banqueParDate.merge(pb.getDate(), pb.getNombrePassages(), Integer::sum);
            detailsBanqueMap.putIfAbsent(pb.getDate(), pb);
        }

        for (PassageJournalier pt : passagesTransporteur) {
            detailsTransporteurMap.put(pt.getDate(), pt);
        }
        

        Set<LocalDate> toutesLesDates = new TreeSet<>();
        toutesLesDates.addAll(banqueParDate.keySet());
        toutesLesDates.addAll(detailsTransporteurMap.keySet());

        List<RapportComparaisonParJour> rapports = new ArrayList<>();

        for (LocalDate date : toutesLesDates) {
            int nbBanque = banqueParDate.getOrDefault(date, 0);
            PassageBanque detailBanque = detailsBanqueMap.get(date);
            PassageJournalier detailTransporteur = detailsTransporteurMap.get(date);

            int nbTransport = detailTransporteur != null ? detailTransporteur.getNbOrdresPassage() : 0;
            double montantTransport = detailTransporteur != null ? detailTransporteur.getMontant() : 0.0;

            RapportComparaisonParJour rc = new RapportComparaisonParJour();
            rc.setDate(date);
            rc.setNbPassagesBanque(nbBanque);
            rc.setNbPassagesTransporteur(nbTransport);
            rc.setMontantTransporteur(montantTransport);

            // Calcul des écarts
            int ecartPassages = nbBanque - nbTransport;
            double ecartMontant = nbBanque == 0
                ? montantTransport
                : Math.abs(ecartPassages) * (detailTransporteur != null ? (detailTransporteur.getTrp() + detailTransporteur.getTrt()) : 0);

            rc.setEcartPassages(ecartPassages);
            rc.setEcartMontant(ecartMontant);

            // Comparaison "equal"
            boolean isEqual =
                ecartPassages == 0 &&
                ecartMontant < 0.01;

            rc.setEqual(isEqual);

            // Détails côté Banque
            if (detailBanque != null) {
                DetailBanque db = new DetailBanque();
                db.setDate(date);
                db.setAgsa(detailBanque.getAgsa());
                db.setType(detailBanque.getType());
                db.setNbPassages(detailBanque.getNombrePassages());
                db.setNomp(detailBanque.getNomp());
                rc.setDetailsBanque(db);
            }

            // Détails côté Transporteur
            if (detailTransporteur != null) {
                DetailTransporteur dt = new DetailTransporteur();
                dt.setDate(date);
                dt.setNbPassages(detailTransporteur.getNbOrdresPassage());
                dt.setTrp(detailTransporteur.getTrp());
                dt.setTrt(detailTransporteur.getTrt());
                dt.setMontant(detailTransporteur.getMontant()); 
                dt.setNature(detailTransporteur.getNature());
                rc.setDetailsTransporteur(dt);
            }

            rapports.add(rc);
        }

        return rapports;
    }
    
} 
