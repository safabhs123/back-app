package org.fsegs.BelhadjsalahSafa.Controlleur;

	import org.fsegs.BelhadjsalahSafa.Service.ORCService;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.multipart.MultipartFile;

	import java.io.File;
	import java.io.IOException;

	@RestController
	@RequestMapping("/api/ocr")

public class ORCControlleur {

	    @Autowired
	    private ORCService ocrService;

	    @PostMapping("/extract")
	    public ResponseEntity<String> extractText(@RequestParam("image") MultipartFile image) {
	        try {
	            File tempFile = File.createTempFile("upload-", image.getOriginalFilename());
	            image.transferTo(tempFile);

	            String text = ocrService.extractTextFromImage(tempFile);
	            return ResponseEntity.ok(text);

	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Erreur de traitement de l’image.");
	        }
	    }
	}



