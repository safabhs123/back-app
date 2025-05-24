package org.fsegs.BelhadjsalahSafa.Service;

import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;
import org.fsegs.BelhadjsalahSafa.Repository.TransporteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteurService {

    @Autowired
    private TransporteurRepository transporteurRepository;

    public List<Transporteur> findAll() {
        return transporteurRepository.findAll();
    }

    public Transporteur save(Transporteur transporteur) {
        return transporteurRepository.save(transporteur);
    }

    public void delete(Long id) {
        transporteurRepository.deleteById(id);
    }
}

