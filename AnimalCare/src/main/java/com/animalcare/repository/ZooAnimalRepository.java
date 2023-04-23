package com.animalcare.repository;

import com.animalcare.model.ZooAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooAnimalRepository extends JpaRepository<ZooAnimal, Integer> {
}