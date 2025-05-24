package org.fsegs.BelhadjsalahSafa.Controlleur;

import java.util.List;
import java.util.Optional;

import org.fsegs.BelhadjsalahSafa.Entity.Caisse;
import org.fsegs.BelhadjsalahSafa.Entity.Region;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteurr;
import org.fsegs.BelhadjsalahSafa.Entity.TypeFonds;
import org.fsegs.BelhadjsalahSafa.Repository.CaisseRepository;
import org.fsegs.BelhadjsalahSafa.Service.CaisseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/caisses")
public class CaisseController {
    @Autowired
    private CaisseRepository caisseRepository;

    @GetMapping
    public List<Caisse> getAll() {
        return caisseRepository.findAll();
    }

    @PostMapping
    public Caisse create(@RequestBody Caisse caisse) {
        return caisseRepository.save(caisse);
    }

    @PutMapping("/{id}")
    public Caisse update(@PathVariable Long id, @RequestBody Caisse caisse) {
        caisse.setId(id);
        return caisseRepository.save(caisse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        caisseRepository.deleteById(id);
    }
}