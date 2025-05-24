package org.fsegs.BelhadjsalahSafa.Controlleur;


import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;
import org.fsegs.BelhadjsalahSafa.Repository.TransporteurRepository;
import org.fsegs.BelhadjsalahSafa.Service.TransporteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transporteurs")
public class TransporteurController {
    @Autowired
    private TransporteurRepository transporteurRepository;

    @GetMapping
    public List<Transporteur> getAll() {
        return transporteurRepository.findAll();
    }

    @PostMapping
    public Transporteur create(@RequestBody Transporteur t) {
        return transporteurRepository.save(t);
    }

    @PutMapping("/{id}")
    public Transporteur update(@PathVariable Long id, @RequestBody Transporteur t) {
        t.setId(id);
        return transporteurRepository.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transporteurRepository.deleteById(id);
    }
}
