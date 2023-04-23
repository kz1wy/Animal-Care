package com.animalcare.repository;

import com.animalcare.model.BreedingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedingPairRepository extends JpaRepository<BreedingPair, Integer> {
}