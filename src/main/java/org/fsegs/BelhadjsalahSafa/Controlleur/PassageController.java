package org.fsegs.BelhadjsalahSafa.Controlleur;



	import org.fsegs.BelhadjsalahSafa.Service.PassageService;
	import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.multipart.MultipartFile;

	import java.util.List;

	@RestController
	@RequestMapping("/api/passages")
	public class PassageController {

	    @Autowired
	    private PassageService passageService;

	    @PostMapping("/transporteur")
	    public ResponseEntity<List<PassageJournalier>> uploadFichierTransporteur(
	            @RequestParam("file") MultipartFile file) {

	        System.out.println("Fichier reçu : " + file.getOriginalFilename());

	        try {
	            String nomFichier = file.getOriginalFilename();
	            List<PassageJournalier> resultats =
	                    passageService.lireFichierTransporteur(file.getInputStream(), nomFichier);
	            return ResponseEntity.ok(resultats);
	        } catch (Exception e) {
	            e.printStackTrace(); // Pour déboguer les erreurs
	            return ResponseEntity.badRequest().build();
	        }
	    }
	}


/*import java.util.List;

import org.fsegs.BelhadjsalahSafa.Service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/passages")
public class PassageController {

    @Autowired
    private PassageService passageService;

    @PostMapping("/transporteur")
    public ResponseEntity<List<PassageJournalier>> uploadFichierTransporteur(
    		@RequestParam("file") MultipartFile file,
    		
    		    @RequestParam("mois") int mois,
    		    @RequestParam("annee") int annee) {
 {
        System.out.println("Fichier reçu : " + file.getOriginalFilename());
        try {
            String nomFichier = file.getOriginalFilename();
            List<PassageJournalier> resultats = passageService.lireFichierTransporteur(file.getInputStream(), nomFichier);
            return ResponseEntity.ok(resultats);
        } catch (Exception e) {
            e.printStackTrace(); // Pour voir l'erreur exacte
            return ResponseEntity.badRequest().build();
        }
    }

    }
}-*/


