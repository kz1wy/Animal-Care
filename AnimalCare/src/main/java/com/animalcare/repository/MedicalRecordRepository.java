package com.animalcare.repository;

import com.animalcare.model.MedicalRecord;
import com.animalcare.model.ZooAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findByAnimal(ZooAnimal animal);
}