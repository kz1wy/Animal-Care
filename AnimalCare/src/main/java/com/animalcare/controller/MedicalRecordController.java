package com.animalcare.controller;

import com.animalcare.model.MedicalRecord;
import com.animalcare.model.ZooAnimal;
import com.animalcare.service.MedicalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("")
    public String showAllMedicalRecords(Model model) {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        model.addAttribute("medicalRecords", medicalRecords);
        return "medical_records/index";
    }

    @GetMapping("/{id}")
    public String showMedicalRecordById(@PathVariable int id, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.findById(id);
        model.addAttribute("medicalRecord", medicalRecord);
        return "medical_records/show";
    }

    @GetMapping("/animal/{animalId}")
    public String showMedicalRecordsByAnimal(@PathVariable int animalId, Model model) {
        ZooAnimal animal = new ZooAnimal();
        animal.setId(animalId);
        List<MedicalRecord> medicalRecords = medicalRecordService.findByAnimal(animal);
        model.addAttribute("medicalRecords", medicalRecords);
        return "medical_records/index";
    }

    @GetMapping("/create")
    public String showCreateMedicalRecordForm(Model model) {
        MedicalRecord medicalRecord = new MedicalRecord();
        model.addAttribute("medicalRecord", medicalRecord);
        return "medical_records/create";
    }

    @PostMapping("/create")
    public String createMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        medicalRecordService.create(medicalRecord);
        return "redirect:/medical-records";
    }

    @GetMapping("/{id}/edit")
    public String showEditMedicalRecordForm(@PathVariable int id, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.findById(id);
        model.addAttribute("medicalRecord", medicalRecord);
        return "medical_records/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMedicalRecord(@PathVariable int id, @ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        medicalRecordService.update(id, medicalRecord);
        return "redirect:/medical-records/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteMedicalRecord(@PathVariable int id) {
        medicalRecordService.delete(id);
        return "redirect:/medical-records";
    }
}
