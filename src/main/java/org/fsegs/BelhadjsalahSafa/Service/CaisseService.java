package org.fsegs.BelhadjsalahSafa.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.fsegs.BelhadjsalahSafa.Entity.Caisse;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;
import org.fsegs.BelhadjsalahSafa.Repository.CaisseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CaisseService {

    @Autowired
    private CaisseRepository caisseRepository;

    public List<Transporteur> getTransporteursParCaisse(Long caisseId) {
        Caisse caisse = caisseRepository.findById(caisseId)
                .orElseThrow(() -> new RuntimeException("Caisse introuvable"));
        return caisse.getRegion().getTransporteurs();
    }


    public Caisse update(Long id, Caisse caisse) {
        caisse.setId(id);
        return caisseRepository.save(caisse);
    }
}
