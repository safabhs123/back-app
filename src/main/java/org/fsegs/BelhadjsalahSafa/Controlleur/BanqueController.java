package org.fsegs.BelhadjsalahSafa.Controlleur;


import org.fsegs.BelhadjsalahSafa.Entity.PassageBanque;
import org.fsegs.BelhadjsalahSafa.Service.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/banque")
public class BanqueController {

    @Autowired
    private BanqueService banqueService;

    // Upload fichier Excel banque + lecture
    @PostMapping("/upload")
    public ResponseEntity<List<PassageBanque>> uploadFichierBanque(@RequestParam("file") MultipartFile file) {
        try {
            List<PassageBanque> passages = banqueService.lireFichierExcelBanque(file.getInputStream());
            return ResponseEntity.ok(passages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Lecture passages banque via requête SQL (par période)
    @GetMapping("/passages")
    public ResponseEntity<List<PassageBanque>> getPassages(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<PassageBanque> passages = banqueService.getPassagesDepuisBase(startDate, endDate);
        return ResponseEntity.ok(passages);
    }
}

