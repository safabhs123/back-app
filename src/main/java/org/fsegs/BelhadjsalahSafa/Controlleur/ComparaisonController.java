// ✅ BACKEND — ComparaisonController.java
package org.fsegs.BelhadjsalahSafa.Controlleur;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.fsegs.BelhadjsalahSafa.Entity.ComparaisonDetail;
import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.fsegs.BelhadjsalahSafa.Entity.PassageDetail;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour;
import org.fsegs.BelhadjsalahSafa.Service.BanqueService;
import org.fsegs.BelhadjsalahSafa.Service.ComparaisonService;
import org.fsegs.BelhadjsalahSafa.Service.ComparaisonService.RapportComparaison;
import org.fsegs.BelhadjsalahSafa.Service.PassageService;
import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.fsegs.BelhadjsalahSafa.Entity.DetailsBanque;

@RestController
@RequestMapping("/api/comparaison")
@CrossOrigin(origins = "http://localhost:3000")
public class ComparaisonController {

    @Autowired
    private BanqueService banqueService;

    @Autowired
    private PassageService passageService;

    @Autowired
    private ComparaisonService comparaisonService;

    @PostMapping("/upload")
    public ResponseEntity<?> comparerDepuisFichiers(
            @RequestParam("fichierBanque") MultipartFile fichierBanque,
            @RequestParam("fichierTransporteur") MultipartFile fichierTransporteur,
            @RequestParam(name = "tolerance", defaultValue = "1") int tolerance) {

        try {
            // 1. Lire les données depuis les fichiers Excel
        	List<PassageBanque> passagesBanque = banqueService.lireFichierExcelBanque(fichierBanque.getInputStream());
        	List<PassageJournalier> passagesTransporteur = passageService.lireFichierTransporteur(
        		    fichierTransporteur.getInputStream(), 
        		    fichierTransporteur.getOriginalFilename()
        		);
            // 2. Appeler la méthode fusionnée
            List<RapportComparaisonParJour> rapport = comparaisonService.comparer(
                    passagesBanque, passagesTransporteur, tolerance
            );

            return ResponseEntity.ok(rapport);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        
    }
  /*  @PostMapping("/save")
    public ResponseEntity<String> saveComparaison(@RequestBody List<RapportComparaisonParJour> comparaisons) {
        // ici tu appelles un service qui sauvegarde en base de données
        comparaisonService.saveComparaison(comparaisons);
        return ResponseEntity.ok("Comparaison sauvegardée avec succès");
    }*/
}


    /*@PostMapping("/comparer-tolerance-stricte")
    public ResponseEntity<List<ComparaisonService.RapportComparaison>> comparerAvecToléranceStricte(
            @RequestParam("banque") MultipartFile fichierBanque,
            @RequestParam("transporteur") MultipartFile fichierTransporteur) throws Exception {

        List<PassageBanque> passagesBanque = banqueService.lireFichierExcelBanque(fichierBanque.getInputStream());
        List<PassageJournalier> passagesTransporteur = passageService.lireFichierTransporteur(
        	    fichierTransporteur.getInputStream(),
        	    fichierTransporteur.getOriginalFilename()
        	);
        List<ComparaisonService.RapportComparaison> resultats =
                comparaisonService.comparerAvecToléranceStricte(passagesBanque, passagesTransporteur);

        return ResponseEntity.ok(resultats);
    }
}*/
/*@RestController
@RequestMapping("/api/comparaison")
public class ComparaisonController {

    @Autowired
    private ComparaisonService comparaisonService;

    @PostMapping("/verifier")
    public ResponseEntity<?> verifierEtComparer(@RequestBody ComparaisonDetail details) {
        try {
            // Tu passes les objets comme ils sont (pas de conversion)
            List<DetailsBanque> banqueDetails = details.getBanqueDetails();
            List<PassageDetail> transporteurDetails = details.getTransporteurDetails();

            List<RapportComparaison> result = comparaisonService
                .comparerDepuisDetails(banqueDetails, transporteurDetails);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
*/



