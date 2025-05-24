package org.fsegs.BelhadjsalahSafa.Controlleur;

import java.io.IOException;
import java.util.List;

import org.fsegs.BelhadjsalahSafa.Entity.DetailsBanque;
import org.fsegs.BelhadjsalahSafa.Entity.DetailsTransporteur;
import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonParJour;
import org.fsegs.BelhadjsalahSafa.Service.BanqueService;
import org.fsegs.BelhadjsalahSafa.Service.PassageService;
import org.fsegs.BelhadjsalahSafa.Service.PassageService.PassageJournalier;
import org.fsegs.BelhadjsalahSafa.Service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/rapport")
@CrossOrigin(origins = "*")
public class RapportControlleur {

    @Autowired
    private BanqueService banqueService;

    @Autowired
    private PassageService passageService;

    @Autowired
    private RapportService rapportService;

    @PostMapping("/generer")
    public List<RapportComparaisonParJour> genererRapport(
            @RequestParam("banque") MultipartFile banqueFile,
            @RequestParam("transport") MultipartFile transportFile) throws Exception {

    	List<PassageBanque> banqueData = banqueService.lireFichierExcelBanque(banqueFile.getInputStream());
    	List<PassageJournalier> transportData = passageService.lireFichierTransporteur(
    	    transportFile.getInputStream(), transportFile.getOriginalFilename());
  // <- ajoute le nom

        return rapportService.genererRapportParJour(banqueData, transportData);
    }
    



}
