package com.animalcare.controller;

import com.animalcare.model.*;
import com.animalcare.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ZooAnimalService zooAnimalService;
    private final EnclosureService enclosureService;
    private final MedicalRecordService medicalRecordService;
    private final HealthRecordService healthRecordService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("message", "Welcome to Animal Care!");
        return "index";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/log-out")
    public String logOutPage(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/user")
    public String showUserPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            //tong so
            List<ZooAnimal> animalList = zooAnimalService.findAll();
            String count1 = Integer.toString(animalList.size());
            model.addAttribute("count1",count1);

            //loai
            String count2 = count1;
            model.addAttribute("count2",count2);

            //chuong
            List<Enclosure> enclosureList = enclosureService.findAllEnclosures();
            String count3 = Integer.toString(enclosureList.size());
            model.addAttribute("count3",count3);

            //medical records
            List<MedicalRecord> medicalRecordList = medicalRecordService.findAll();
            model.addAttribute("medRList",medicalRecordList);

            //health records
            List<HealthRecord> healthRecordList = healthRecordService.getAllHealthRecords();
            model.addAttribute("healthRList",healthRecordList);

            //animal
            model.addAttribute("animalList", animalList);
            return "user/index";
        } else {
            return "redirect:/index";
        }
    }
}