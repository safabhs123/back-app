package org.fsegs.BelhadjsalahSafa.Service;

import org.fsegs.BelhadjsalahSafa.Entity.LDAP;
import org.fsegs.BelhadjsalahSafa.Entity.Utilisateur;
import org.fsegs.BelhadjsalahSafa.Repository.LdapRepository;
import org.fsegs.BelhadjsalahSafa.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LDAPService {

    @Autowired
    private LdapRepository ldapRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public String getRoleByMatriculeAndPassword(String matricule, String password) {
        System.out.println("Recherche de l'utilisateur avec le matricule : " + matricule);
        Optional<LDAP> userOpt = ldapRepository.findByMatricule(matricule);

        if (userOpt.isPresent()) {
            LDAP user = userOpt.get();
            System.out.println("Utilisateur trouvé dans LDAP : " + user.getMatricule());

            if (user.getPassword().equals(password)) {
                // Vérifie si l'utilisateur existe déjà dans la base MySQL
                Optional<Utilisateur> existing = utilisateurRepository.findByMatricule(matricule);
                if (existing.isEmpty()) {
                    Utilisateur newUser = new Utilisateur();
                    newUser.setMatricule(user.getMatricule());
                    newUser.setNom(""); // Remplace par user.getNom() si disponible
                    newUser.setPrenom(""); // Remplace par user.getPrenom() si disponible
                    newUser.setRole(user.getRole());
                    newUser.setDateDebutTravail(LocalDate.now());
                    newUser.setPassword(user.getPassword());
                    utilisateurRepository.save(newUser); // Sauvegarde l'utilisateur dans la base MySQL
                    System.out.println("Utilisateur ajouté dans MySQL : " + newUser.getMatricule());
                }

                return user.getRole().toString();  // Retourne le rôle trouvé
            } else {
                System.out.println("Mot de passe incorrect pour : " + matricule);
                throw new RuntimeException("Mot de passe incorrect");
            }
        } else {
            System.out.println("Utilisateur introuvable dans LDAP : " + matricule);
            throw new RuntimeException("Utilisateur introuvable dans LDAP");
        }
    }

}
