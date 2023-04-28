package com.animalcare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("message", "Welcome to Animal Care!");
        return "index";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAdminPage(Authentication authentication) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        return "admin/index";
    }
}