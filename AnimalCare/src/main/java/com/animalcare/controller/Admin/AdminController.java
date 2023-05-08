package com.animalcare.controller.Admin;
import com.animalcare.model.User;
import com.animalcare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("")
    public String showDashBoard(Model model) {
        model.addAttribute("message", "Welcome to Animal Care!");
        return "admin/index";
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

}