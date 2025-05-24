package org.fsegs.BelhadjsalahSafa.Controlleur;
import org.fsegs.BelhadjsalahSafa.Entity.Role;
import org.fsegs.BelhadjsalahSafa.Entity.Utilisateur;
import org.fsegs.BelhadjsalahSafa.Repository.UtilisateurRepository;
import org.fsegs.BelhadjsalahSafa.dto.LoginRequest;
import org.fsegs.BelhadjsalahSafa.dto.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LdapControlleur {

    private final UtilisateurRepository utilisateurRepository;

    // Constructeur d'injection de dépendance pour le repository
    public LdapControlleur(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByMatricule(loginRequest.getMatricule());
        
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            
            // Comparaison du mot de passe en texte brut
            if (loginRequest.getPassword().equals(utilisateur.getPassword())) {
                // Vérification du rôle et assignation avec valeur par défaut si null
                Role roleEnum = utilisateur.getRole();
                if (roleEnum == null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                         .body("Le rôle de l'utilisateur est manquant.");
                }
                
                String role = roleEnum.toString(); // On récupère la chaîne de caractères du rôle
                
                // Retourner le loginResponse avec matricule et rôle
                return ResponseEntity.ok(new LoginResponse(utilisateur.getMatricule(),utilisateur.getRole()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Mot de passe incorrect");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }

}

