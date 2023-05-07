package com.animalcare.controller.Admin;
import com.animalcare.model.User;
import com.animalcare.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.ui.Model;
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showDashBoard(Model model) {
        model.addAttribute("message", "Welcome to Animal Care!");
        return "admin/index";
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<User> listUser = userService.getAllUsers();
            model.addAttribute("listUser",listUser);
            return "admin/index";
        } else {
            return "redirect:/index";
        }
    }
//  @GetMapping("/add-user")
//  public String showAddUserPage(Model model) {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//     if (authentication != null && authentication.isAuthenticated()) {
//         User user = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getRole(), request.getCreatedAt());
//         model.addAttribute("user",user);
//       return "admin/add-user";
//       } else {
//           return "redirect:/index";
//       }
//   }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);

    }
    @PostMapping(value = "/add-user")
    public String createUser(@ModelAttribute User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            userService.createUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/add-user";
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

}