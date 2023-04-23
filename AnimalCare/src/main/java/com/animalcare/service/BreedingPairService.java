package com.animalcare.service;

import com.animalcare.model.BreedingPair;
import com.animalcare.repository.BreedingPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BreedingPairService {

    private final BreedingPairRepository breedingPairRepository;

    public BreedingPairService(BreedingPairRepository breedingPairRepository) {
        this.breedingPairRepository = breedingPairRepository;
    }

    public List<BreedingPair> findAll() {
        return breedingPairRepository.findAll();
    }

    public Optional<BreedingPair> findById(int id) {
        return breedingPairRepository.findById(id);
    }

    public BreedingPair save(BreedingPair breedingPair) {
        LocalDateTime now = LocalDateTime.now();
        breedingPair.setCreatedAt(now);
        return breedingPairRepository.save(breedingPair);
    }

    public BreedingPair update(int id, BreedingPair breedingPair) throws Exception {
        Optional<BreedingPair> existingBreedingPair = breedingPairRepository.findById(id);

        if (existingBreedingPair.isEmpty()) {
            throw new Exception("Breeding pair not found");
        }

        BreedingPair updatedBreedingPair = existingBreedingPair.get();
        updatedBreedingPair.setBreedingProgram(breedingPair.getBreedingProgram());
        updatedBreedingPair.setMaleAnimal(breedingPair.getMaleAnimal());
        updatedBreedingPair.setFemaleAnimal(breedingPair.getFemaleAnimal());
        updatedBreedingPair.setBreedingNotes(breedingPair.getBreedingNotes());
        return breedingPairRepository.save(updatedBreedingPair);
    }

    public void deleteById(int id) {
        breedingPairRepository.deleteById(id);
    }
}
