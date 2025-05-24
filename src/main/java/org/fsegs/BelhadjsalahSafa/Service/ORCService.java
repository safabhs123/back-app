package org.fsegs.BelhadjsalahSafa.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ORCService {

    public String extractTextFromImage(File imageFile) {
        Tesseract tesseract = new Tesseract();

        // Spécifie le chemin de Tesseract si ce n’est pas dans le PATH système
        tesseract.setDatapath("C:\\Users\\HP\\tesseract\\tessdata"); // adapte ce chemin si besoin
        tesseract.setLanguage("fra"); // ou "eng" pour anglais

        try {
            return tesseract.doOCR(imageFile);
        } catch (TesseractException e) {
            return "Erreur OCR : " + e.getMessage();
        }
    }
}

