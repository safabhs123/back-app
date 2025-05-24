package org.fsegs.BelhadjsalahSafa.Repository;

import org.fsegs.BelhadjsalahSafa.Entity.RapportComparaisonn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportComparaisonRepository extends JpaRepository<RapportComparaisonn, Long> {
}

