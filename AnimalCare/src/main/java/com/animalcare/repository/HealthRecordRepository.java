package com.animalcare.repository;

import com.animalcare.model.HealthRecord;
import com.animalcare.model.ZooAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    List<HealthRecord> findByAnimalOrderByDateOfVisitDesc(ZooAnimal animal);
}