package org.fsegs.BelhadjsalahSafa.Controlleur;

import java.util.List;
import java.util.Map;

import org.fsegs.BelhadjsalahSafa.Entity.Region;
import org.fsegs.BelhadjsalahSafa.Repository.RegionRepository;
import org.fsegs.BelhadjsalahSafa.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionRepository.save(region);
    }

    @PutMapping("/{id}")
    public Region update(@PathVariable Long id, @RequestBody Region region) {
        region.setId(id);
        return regionRepository.save(region);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable Long id) {
        Region region = regionService.getRegionWithTransporteurs(id);
        return ResponseEntity.ok(region);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        regionRepository.deleteById(id);
    }

 // Ajouter un transporteur à une région
    @PostMapping("/{regionId}/transporteurs")
    public ResponseEntity<?> addTransporteurToRegion(
            @PathVariable Long regionId,
            @RequestBody Map<String, Long> body) {
        Long transporteurId = body.get("transporteurId");
        regionService.addTransporteurToRegion(regionId, transporteurId);
        return ResponseEntity.ok().build();
    }

    // Supprimer un transporteur d’une région
    @DeleteMapping("/{regionId}/transporteurs/{transporteurId}")
    public ResponseEntity<?> removeTransporteurFromRegion(
            @PathVariable Long regionId,
            @PathVariable Long transporteurId) {
        regionService.removeTransporteurFromRegion(regionId, transporteurId);
        return ResponseEntity.ok().build();
    }

}