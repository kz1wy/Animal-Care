package com.animalcare.service;

import com.animalcare.model.HealthRecord;
import com.animalcare.model.ZooAnimal;
import com.animalcare.repository.HealthRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordService {

    private HealthRecordRepository healthRecordRepository;
    public HealthRecordService(HealthRecordRepository healthRecordRepository){
        this.healthRecordRepository = healthRecordRepository;
    }

    public HealthRecord saveHealthRecord(HealthRecord healthRecord) {
        healthRecord.setCreatedAt(LocalDateTime.now());
        return healthRecordRepository.save(healthRecord);
    }

    public HealthRecord getHealthRecordById(int id) {
        Optional<HealthRecord> optionalHealthRecord = healthRecordRepository.findById(id);
        return optionalHealthRecord.orElse(null);
    }

    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    public void deleteHealthRecord(int id) {
        healthRecordRepository.deleteById(id);
    }

    public List<HealthRecord> getHealthRecordsByAnimal(ZooAnimal animal) {
        return healthRecordRepository.findByAnimalOrderByDateOfVisitDesc(animal);
    }
}
