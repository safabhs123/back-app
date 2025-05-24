package org.fsegs.BelhadjsalahSafa.Repository;



import org.fsegs.BelhadjsalahSafa.Entity.LDAP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LdapRepository extends JpaRepository<LDAP, String> {
	 boolean existsByMatricule(String matricule);
	Optional<LDAP> findByMatricule(String matricule); // Nom exact du champ

   
}

