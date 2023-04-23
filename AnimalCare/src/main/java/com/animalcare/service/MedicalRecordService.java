package com.animalcare.service;

import com.animalcare.model.MedicalRecord;
import com.animalcare.model.ZooAnimal;
import com.animalcare.repository.MedicalRecordRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord create(MedicalRecord medicalRecord) {
        medicalRecord.setCreatedAt(LocalDateTime.now());
        return medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord findById(int id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);
        if (medicalRecord.isPresent()) {
            return medicalRecord.get();
        } else {
            throw new EntityNotFoundException("MedicalRecord not found with ID " + id);
        }
    }

    public List<MedicalRecord> findByAnimal(ZooAnimal animal) {
        return medicalRecordRepository.findByAnimal(animal);
    }

    public MedicalRecord update(int id, MedicalRecord medicalRecord) {
        MedicalRecord existingMedicalRecord = findById(id);
        existingMedicalRecord.setAnimal(medicalRecord.getAnimal());
        existingMedicalRecord.setVeterinarianName(medicalRecord.getVeterinarianName());
        existingMedicalRecord.setDateOfVisit(medicalRecord.getDateOfVisit());
        existingMedicalRecord.setDiagnosis(medicalRecord.getDiagnosis());
        existingMedicalRecord.setPrescription(medicalRecord.getPrescription());
        existingMedicalRecord.setNotes(medicalRecord.getNotes());
        return medicalRecordRepository.save(existingMedicalRecord);
    }

    public void delete(int id) {
        medicalRecordRepository.deleteById(id);
    }
}
