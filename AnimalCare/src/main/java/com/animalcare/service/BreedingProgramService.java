package com.animalcare.service;

import com.animalcare.model.BreedingProgram;
import com.animalcare.repository.BreedingProgramRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BreedingProgramService {

    private final BreedingProgramRepository breedingProgramRepository;

    public BreedingProgramService(BreedingProgramRepository breedingProgramRepository) {
        this.breedingProgramRepository = breedingProgramRepository;
    }

    public BreedingProgram create(BreedingProgram breedingProgram) {
        breedingProgram.setCreatedAt(LocalDateTime.now());
        return breedingProgramRepository.save(breedingProgram);
    }

    public List<BreedingProgram> findAll() {
        return breedingProgramRepository.findAll();
    }

    public BreedingProgram findById(int id) {
        Optional<BreedingProgram> breedingProgram = breedingProgramRepository.findById(id);
        if (breedingProgram.isPresent()) {
            return breedingProgram.get();
        } else {
            throw new EntityNotFoundException("BreedingProgram not found with ID " + id);
        }
    }

    public BreedingProgram update(int id, BreedingProgram breedingProgram) {
        BreedingProgram existingBreedingProgram = findById(id);
        existingBreedingProgram.setSpecies(breedingProgram.getSpecies());
        existingBreedingProgram.setStartDate(breedingProgram.getStartDate());
        existingBreedingProgram.setEndDate(breedingProgram.getEndDate());
        existingBreedingProgram.setBreedingNotes(breedingProgram.getBreedingNotes());
        return breedingProgramRepository.save(existingBreedingProgram);
    }

    public void delete(int id) {
        breedingProgramRepository.deleteById(id);
    }
}
