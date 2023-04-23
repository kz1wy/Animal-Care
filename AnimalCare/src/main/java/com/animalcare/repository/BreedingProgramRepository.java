package com.animalcare.repository;
import com.animalcare.model.BreedingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedingProgramRepository extends JpaRepository<BreedingProgram, Integer> {
}