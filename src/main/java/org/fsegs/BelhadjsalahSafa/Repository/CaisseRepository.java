package org.fsegs.BelhadjsalahSafa.Repository;

import org.fsegs.BelhadjsalahSafa.Entity.Caisse;
import org.fsegs.BelhadjsalahSafa.Entity.Region;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteurr;
import org.fsegs.BelhadjsalahSafa.Entity.TypeFonds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CaisseRepository extends JpaRepository<Caisse, Long> {
}



