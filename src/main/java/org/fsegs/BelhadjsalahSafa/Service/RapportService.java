package org.fsegs.BelhadjsalahSafa.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.fsegs.BelhadjsalahSafa.Entity.PassageTransport;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour;
import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
import org.springframework.stereotype.Service;


@Service
public class RapportService {

    public List<RapportComparaisonParJour> genererRapportParJour(
            List<PassageBanque> passageBanque,
            List<PassageJournalier> passagejournalier) {

        // Regrouper nombre de passages banque par date
        Map<LocalDate, Integer> passagesBanque = passageBanque.stream()
            .collect(Collectors.groupingBy(
                PassageBanque::getDate,
                Collectors.summingInt(PassageBanque::getNombrePassages)
            ));

        // Regrouper nombre de passages et montant transporteur par date
        Map<LocalDate, Integer> passagesTransporteur = new HashMap<>();
        Map<LocalDate, Double> montantTransporteur = new HashMap<>();

        for (PassageJournalier t : passagejournalier) {
            LocalDate date = t.getDate();
            int nbPass = t.getNbOrdresPassage();
            double montant = t.getMontant();

            passagesTransporteur.merge(date, nbPass, Integer::sum);
            montantTransporteur.merge(date, montant, Double::sum);
        }

        // Construire la liste des rapports par date
        List<RapportComparaisonParJour> rapports = new ArrayList<>();

        // Ensemble des dates à traiter (banque + transporteur)
        Set<LocalDate> toutesDates = new HashSet<>();
        toutesDates.addAll(passagesBanque.keySet());
        toutesDates.addAll(passagesTransporteur.keySet());

        for (LocalDate date : toutesDates) {
            int nbBanque = passagesBanque.getOrDefault(date, 0);
            int nbTransport = passagesTransporteur.getOrDefault(date, 0);
            double montant = montantTransporteur.getOrDefault(date, 0.0);

            int ecartPassages =nbTransport- nbBanque  ;
            double prixUnitaire = (nbTransport != 0) ? (montant / nbTransport) : 0.0;
            double ecartMontant = ecartPassages * prixUnitaire;
            String statut;
            if (ecartPassages != 0) {
                statut = "Écart passages";
            } else if (Math.abs(ecartMontant) > 0.01) {
                statut = "Écart montant";
            } else {
                statut = "OK";
            }
            if (!"OK".equals(statut)) {
            RapportComparaisonParJour rapport = new RapportComparaisonParJour();
            rapport.setDate(date);
            rapport.setNbPassagesBanque(nbBanque);
            rapport.setNbPassagesTransporteur(nbTransport);
            rapport.setMontantTransporteur(montant);
            rapport.setEcartPassages(ecartPassages);
           // rapport.setEcartMontant(ecartPassages);
            rapport.setEcartMontant(ecartMontant);

            rapport.setStatut(rapport.getStatut());

            rapports.add(rapport);
        }
        }

        // Retourner la liste des rapports par jour
        return rapports;
        
    }
    
}
