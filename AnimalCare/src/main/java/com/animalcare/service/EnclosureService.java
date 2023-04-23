package com.animalcare.service;

import com.animalcare.model.Enclosure;
import com.animalcare.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;

    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public List<Enclosure> findAllEnclosures() {
        return enclosureRepository.findAll();
    }

    public Optional<Enclosure> findEnclosureById(int id) {
        return enclosureRepository.findById(id);
    }

    public Enclosure saveEnclosure(Enclosure enclosure) {
        return enclosureRepository.save(enclosure);
    }

    public Enclosure updateEnclosure(Enclosure enclosure) {
        Optional<Enclosure> optionalEnclosure = enclosureRepository.findById(enclosure.getId());
        if (optionalEnclosure.isPresent()) {
            return enclosureRepository.save(enclosure);
        } else {
            throw new RuntimeException("Enclosure not found with id: " + enclosure.getId());
        }
    }

    public void deleteEnclosureById(int id) {
        enclosureRepository.deleteById(id);
    }
}
