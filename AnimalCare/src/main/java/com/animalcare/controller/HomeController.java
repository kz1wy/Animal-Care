package com.animalcare.controller;

import com.animalcare.model.User;
import com.animalcare.service.UserService;
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
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
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
    public String showUserPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/index";
        } else {
            return "redirect:/index";
        }
    }
    @GetMapping("/user/animal-profiles")
    public String showAnimalProfilesPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/animal-profiles";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/create-animal-profiles")
    public String showCreateAnimalProfilePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/create-animal-profile";
        } else {
            return "redirect:/index";
        }
    }
    @GetMapping("/user/health-record")
    public String showHealthRecordPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/animal-profiles";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/user/create-health-record")
    public String showCreateHealthRecordePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "user/create-health-record";
        } else {
            return "redirect:/index";
        }
    }
}