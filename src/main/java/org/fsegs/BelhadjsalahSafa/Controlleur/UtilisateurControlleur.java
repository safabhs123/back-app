package org.fsegs.BelhadjsalahSafa.Controlleur;

import org.fsegs.BelhadjsalahSafa.Entity.Utilisateur;
import org.fsegs.BelhadjsalahSafa.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin("*")
public class UtilisateurControlleur {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurCree = utilisateurService.ajouterUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurCree);
    }

    // ✅ Corrigé : retourne le rôle (String) via ResponseEntity
    @GetMapping("/{matricule}")
    public ResponseEntity<String> getRoleByMatricule(
            @PathVariable String matricule,
            @RequestParam String password) {

        try {
            String role = utilisateurService.getRoleByMatricule(matricule, password);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @DeleteMapping("/delete/{matricule}")
    public void supprimerUtilisateur(@PathVariable String matricule) {
        utilisateurService.supprimerUtilisateur(matricule);
    }
    @GetMapping("/profil/{matricule}")
    public ResponseEntity<Utilisateur> getUtilisateurByMatricule(@PathVariable String matricule) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByMatricule(matricule);
            return ResponseEntity.ok(utilisateur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Utilisateur()); // Ou une réponse d'erreur plus appropriée
        }
    }

    @PutMapping("/update/{matricule}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(
            @PathVariable String matricule,
            @RequestBody Utilisateur utilisateurModifie) {

        Utilisateur utilisateur = utilisateurService.updateUtilisateur(matricule, utilisateurModifie);
        return ResponseEntity.ok(utilisateur);
    }

}
