package com.animalcare.controller;

import com.animalcare.model.ZooAnimal;
import com.animalcare.service.ZooAnimalService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/zoo-animals")
public class ZooAnimalController {

    private final ZooAnimalService zooAnimalService;

    public ZooAnimalController(ZooAnimalService zooAnimalService) {
        this.zooAnimalService = zooAnimalService;
    }

    @GetMapping("")
    public String getAllZooAnimals(Model model) {
        List<ZooAnimal> zooAnimals = zooAnimalService.findAll();
        model.addAttribute("zooAnimals", zooAnimals);
        return "user/animal-profiles";
    }

    @GetMapping("/{id}")
    public String getZooAnimalById(@PathVariable int id, Model model) {
        ZooAnimal zooAnimal = zooAnimalService.findById(id);
        model.addAttribute("zooAnimal", zooAnimal);
        return "zoo-animals/details";
    }

    @GetMapping("/add")
    public String showAddZooAnimalForm(Model model) {
        model.addAttribute("zooAnimal", new ZooAnimal());
        return "user/create-animal-profile";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','VETERINARIAN')")
    public String addZooAnimal(@ModelAttribute ZooAnimal zooAnimal) {
        zooAnimalService.create(zooAnimal);
        return "redirect:/zoo-animals";
    }

    @GetMapping("/{id}/edit")
    public String showEditZooAnimalForm(@PathVariable int id, Model model) {
        ZooAnimal zooAnimal = zooAnimalService.findById(id);
        model.addAttribute("zooAnimal", zooAnimal);
        return "zoo-animals/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority('ADMIN','VETERINARIAN')")
    public String editZooAnimal(@PathVariable int id, @ModelAttribute ZooAnimal zooAnimal) {
        zooAnimalService.update(id, zooAnimal);
        return "redirect:/zoo-animals";
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN','VETERINARIAN')")
    public String deleteZooAnimal(@PathVariable int id,  RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Deleted successfully");
        zooAnimalService.delete(id);
        return "redirect:/zoo-animals";
    }
}
