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
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
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

//    @GetMapping("/admin")
//    public String getAllUsers(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            List<User> listUser = userService.getAllUsers();
//            model.addAttribute("listUser",listUser);
//            return "admin/index";
//        } else {
//            return "redirect:/index";
//        }
//    }

//    @GetMapping("/admin/add-user")
//    public String showAddUserPage() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return "admin/add-user";
//        } else {
//            return "redirect:/index";
//        }
//    }

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

    @GetMapping("/user/animals")
    public String showAnimalProfilesPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<ZooAnimal> animalList = zooAnimalService.findAll();
            model.addAttribute("animalList", animalList);
            return "user/animal-profiles";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/createanimal")
    public String showCreateAnimalProfilePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/create-animal-profile";
        } else {
            return "redirect:/index";
        }
    }
    @GetMapping("/user/healths")
    public String showHealthRecordPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/animal-profiles";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/createhealth")
    public String showCreateHealthRecordePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/create-health-record";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/notification")
    public String showNotificationPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/noti";
        } else {
            return "redirect:/index";
        }
    }
}