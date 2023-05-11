package com.animalcare.controller.Admin;
import com.animalcare.model.User;
import com.animalcare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("")
    public String showDashBoard(Model model) {
            List<User> listUser = userService.getAllUsers();
            model.addAttribute("listUser",listUser);
            return "admin/index";
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    @PostMapping("/add-user")
    public String createUser(@ModelAttribute User user) {
         userService.createUser(user);
         return "redirect:/admin";
    }
}