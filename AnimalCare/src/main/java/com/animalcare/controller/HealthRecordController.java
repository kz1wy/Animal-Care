package com.animalcare.controller;

import com.animalcare.model.HealthRecord;
import com.animalcare.model.ZooAnimal;
import com.animalcare.service.HealthRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-records")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    public HealthRecordController(HealthRecordService healthRecordService) {
        this.healthRecordService = healthRecordService;
    }

    @GetMapping("")
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecords();
    }

    @GetMapping("/{id}")
    public HealthRecord getHealthRecordById(@PathVariable int id) {
        return healthRecordService.getHealthRecordById(id);
    }

    @PostMapping("")
    public HealthRecord createHealthRecord(@RequestBody HealthRecord healthRecord) {
        return healthRecordService.saveHealthRecord(healthRecord);
    }

    @PutMapping("/{id}")
    public HealthRecord updateHealthRecord(@PathVariable int id, @RequestBody HealthRecord healthRecord) {
        healthRecord.setId(id);
        return healthRecordService.saveHealthRecord(healthRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteHealthRecord(@PathVariable int id) {
        healthRecordService.deleteHealthRecord(id);
    }

    @GetMapping("/animal/{animalId}")
    public List<HealthRecord> getHealthRecordsByAnimal(@PathVariable int animalId) {
        ZooAnimal animal = new ZooAnimal();
        animal.setId(animalId);
        return healthRecordService.getHealthRecordsByAnimal(animal);
    }
}
