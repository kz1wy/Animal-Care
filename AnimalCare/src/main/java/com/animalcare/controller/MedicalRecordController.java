package com.animalcare.controller;

import com.animalcare.model.MedicalRecord;
import com.animalcare.model.ZooAnimal;
import com.animalcare.service.MedicalRecordService;
import com.animalcare.service.ZooAnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private  final ZooAnimalService zooAnimalService;

    public MedicalRecordController(MedicalRecordService medicalRecordService, ZooAnimalService zooAnimalService) {
        this.medicalRecordService = medicalRecordService;
        this.zooAnimalService = zooAnimalService;
    }

    @GetMapping("")
    public String showAllMedicalRecords(Model model) {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        model.addAttribute("medicalRecords", medicalRecords);
        return "user/medical-records";
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
        List<ZooAnimal> zooAnimals = zooAnimalService.findAll();
        model.addAttribute("zooAnimals", zooAnimals);
        return "user/create-medical-record";
    }

    //@PostMapping("/create")
    //public String createMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
    //    medicalRecordService.create(medicalRecord);
    //    return "redirect:/user/medical-records";
    //}
    @PostMapping(value ="/create")
    public String createUser(@ModelAttribute  MedicalRecord medicalRecord) {
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
    @RequestMapping("/delete/{id}")
    public String deleteAnimal(int id) {
        zooAnimalService.delete(id);
        return "redirect:/";
    }
}
