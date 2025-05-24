package org.fsegs.BelhadjsalahSafa.Service;

import org.fsegs.BelhadjsalahSafa.Entity.LDAP;
import org.fsegs.BelhadjsalahSafa.Entity.Utilisateur;
import org.fsegs.BelhadjsalahSafa.Repository.LdapRepository;
import org.fsegs.BelhadjsalahSafa.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private LdapRepository ldapRepo;
    public Utilisateur ajouterUtilisateur(Utilisateur user) {
        // 1. Sauvegarder l'utilisateur
        Utilisateur savedUser = utilisateurRepository.save(user);

        // 2. Ajouter dans la table LDAP automatiquement
        if (!ldapRepo.existsByMatricule(user.getMatricule())) {
           LDAP ldapUser = new LDAP();
            ldapUser.setMatricule(user.getMatricule());
            ldapUser.setPassword(user.getPassword()); // ou encoder si besoin
            ldapUser.setRole(user.getRole());
            ldapRepo.save(ldapUser);
        }

        return savedUser;
    }


   /* public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        // Sauvegarder l'utilisateur dans la base de données et le retourner
        return utilisateurRepository.save(utilisateur);
    }
*/
    public String getRoleByMatriculeAndPassword(String matricule, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByMatriculeAndPassword(matricule, password)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return utilisateur.getRole().name(); // ✅ .name() convertit l'enum Role en String
    }
    public Utilisateur getUtilisateurByMatricule(String matricule) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByMatricule(matricule);
        if (utilisateurOpt.isPresent()) {
            return utilisateurOpt.get();
        } else {
            throw new RuntimeException("Utilisateur non trouvé");
        }
    }
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    public String getRoleByMatricule(String matricule, String password) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByMatricule(matricule);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();

            if (utilisateur.getPassword().equals(password)) {
                return utilisateur.getRole().toString(); // retourne "ADMIN" ou "EMPLOYE"
            } else {
                throw new RuntimeException("Mot de passe incorrect");
            }

        } else {
            throw new RuntimeException("Utilisateur introuvable");
        }
    }

    public void supprimerUtilisateur(String matricule) {
        utilisateurRepository.deleteById(matricule);
    }
    public Utilisateur updateUtilisateur(String matricule, Utilisateur utilisateurModifie) {
        Utilisateur utilisateurExistant = utilisateurRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        /*if (!utilisateurExistant.getRole().equals(utilisateurModifie.getRole())) {
            utilisateurExistant.setDateDebutTravail(LocalDate.now());
        }*/
        utilisateurExistant.setNom(utilisateurModifie.getNom());
        utilisateurExistant.setPrenom(utilisateurModifie.getPrenom());
        utilisateurExistant.setRole(utilisateurModifie.getRole());
        utilisateurExistant.setDateDebutTravail(utilisateurModifie.getDateDebutTravail());

        return utilisateurRepository.save(utilisateurExistant);
    }



    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}
