package com.animalcare.controller;

import com.animalcare.model.Enclosure;
import com.animalcare.service.EnclosureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/enclosures")
public class EnclosureController {

    private final EnclosureService enclosureService;

    public EnclosureController(EnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }

    @GetMapping("")
    public String showAllEnclosures(Model model) {
        List<Enclosure> enclosures = enclosureService.findAllEnclosures();
        model.addAttribute("enclosures", enclosures);
        return "enclosures/index";
    }

    @GetMapping("/{id}")
    public String showEnclosureById(@PathVariable int id, Model model) {
        Optional<Enclosure> optionalEnclosure = enclosureService.findEnclosureById(id);
        if (optionalEnclosure.isPresent()) {
            Enclosure enclosure = optionalEnclosure.get();
            model.addAttribute("enclosure", enclosure);
            return "enclosures/show";
        } else {
            throw new RuntimeException("Enclosure not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public String showCreateEnclosureForm(Model model) {
        Enclosure enclosure = new Enclosure();
        model.addAttribute("enclosure", enclosure);
        return "enclosures/create";
    }

    @PostMapping("/create")
    public String createEnclosure(@ModelAttribute("enclosure") @Valid Enclosure enclosure, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "enclosures/create";
        } else {
            enclosureService.saveEnclosure(enclosure);
            return "redirect:/enclosures";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditEnclosureForm(@PathVariable int id, Model model) {
        Optional<Enclosure> optionalEnclosure = enclosureService.findEnclosureById(id);
        if (optionalEnclosure.isPresent()) {
            Enclosure enclosure = optionalEnclosure.get();
            model.addAttribute("enclosure", enclosure);
            return "enclosures/edit";
        } else {
            throw new RuntimeException("Enclosure not found with id: " + id);
        }
    }

    @PostMapping("/{id}/edit")
    public String editEnclosure(@ModelAttribute("enclosure") @Valid Enclosure enclosure, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "enclosures/edit";
        } else {
            enclosureService.updateEnclosure(enclosure);
            return "redirect:/enclosures/" + enclosure.getId();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEnclosure(@PathVariable int id) {
        enclosureService.deleteEnclosureById(id);
        return "redirect:/enclosures";
    }
}
