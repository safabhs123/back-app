package org.fsegs.BelhadjsalahSafa.Repository;

import org.fsegs.BelhadjsalahSafa.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByMatriculeAndPassword(String matricule, String password);
    void deleteByMatricule(String matricule);
    Optional<Utilisateur> findByMatricule(String matricule);
}



