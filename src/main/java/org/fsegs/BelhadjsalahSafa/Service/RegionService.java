package org.fsegs.BelhadjsalahSafa.Service;

import org.fsegs.BelhadjsalahSafa.Entity.Region;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;
import org.fsegs.BelhadjsalahSafa.Repository.RegionRepository;
import org.fsegs.BelhadjsalahSafa.Repository.TransporteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TransporteurRepository transporteurRepository;

    @Transactional
    public void addTransporteurToRegion(Long regionId, Long transporteurId) {
        Region region = regionRepository.findById(regionId)
            .orElseThrow(() -> new RuntimeException("Région non trouvée"));
        Transporteur transporteur = transporteurRepository.findById(transporteurId)
            .orElseThrow(() -> new RuntimeException("Transporteur non trouvé"));

        region.getTransporteurs().add(transporteur);
        regionRepository.save(region);
    }
    @Transactional
    public Region getRegionWithTransporteurs(Long regionId) {
        Region region = regionRepository.findById(regionId).orElseThrow();
        region.getTransporteurs().size(); // Force l'initialisation (important pour éviter le LazyInitializationException)
        return region;
    }


    @Transactional
    public Region removeTransporteurFromRegion(Long regionId, Long transporteurId) {
        Region region = regionRepository.findById(regionId).orElseThrow();

        // Initialisation explicite de la collection si elle est LAZY
        region.getTransporteurs().size();

        region.getTransporteurs().removeIf(t -> t.getId().equals(transporteurId));
        return regionRepository.save(region);
    }
}
