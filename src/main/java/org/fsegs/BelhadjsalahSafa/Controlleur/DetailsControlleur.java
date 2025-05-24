package org.fsegs.BelhadjsalahSafa.Controlleur;


import org.fsegs.BelhadjsalahSafa.Entity.DetailsBanque;
import org.fsegs.BelhadjsalahSafa.Entity.DetailsTransporteur;
import org.fsegs.BelhadjsalahSafa.Service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

 


@RestController
@RequestMapping("/api/details")
@CrossOrigin(origins = "*")
public class DetailsControlleur {

    @Autowired
    private DetailsService detailsService;

    @PostMapping("/banque/upload")
    public ResponseEntity<?> uploadBanqueExcel(@RequestParam("file") MultipartFile file) {
        try {
            detailsService.chargerDetailsBanque(file.getInputStream());
            return ResponseEntity.ok("Fichier banque importé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'import");
        }
    }
    @GetMapping("/banque")
    public ResponseEntity<List<DetailsBanque>> getDetailsBanqueByDate(@RequestParam String date) {
        try {
            // Remplacer "-" par "/" si nécessaire pour uniformiser
            String normalizedDate = date.replace('-', '/');

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(normalizedDate, formatter);

            String dateIso = localDate.toString(); // "2025-02-21"

            System.out.println("Recherche détails banque pour la date : " + dateIso);

            List<DetailsBanque> details = detailsService.getDetailsBanqueByDate(dateIso);
            return ResponseEntity.ok(details);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
        
    }
    private String normaliserDate(String date) {
        // transforme "21-02-2025" ou "21/02/2025" en "2025-02-21"
        String normalized = date.replace('-', '/');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(normalized, formatter);
        return localDate.toString();
    }
    @PostMapping("/transport/upload")
    public ResponseEntity<String> uploadDetailsTransport(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            detailsService.chargerDetailsTransporteur(inputStream, fileName);

            return ResponseEntity.ok("✅ Détails transporteur chargés avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("❌ Échec du chargement : " + e.getMessage());
        }
    }



    @GetMapping("/transport")
    public ResponseEntity<List<DetailsTransporteur>> getDetailsTransporteurByDate(@RequestParam String date) {
        try {
            // Normalise le format "21-02-2025" ou "21/02/2025" → LocalDate
            String normalizedDate = date.replace('-', '/');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(normalizedDate, formatter);

            String dateIso = localDate.toString(); // "2025-02-21"

            System.out.println("Recherche détails transport pour la date : " + dateIso);

            List<DetailsTransporteur> details = detailsService.getDetailsTransportByDate(dateIso);
            return ResponseEntity.ok(details);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    }






    




