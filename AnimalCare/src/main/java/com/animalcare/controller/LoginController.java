package com.animalcare.controller;

import com.animalcare.config.JwtUtils;
import com.animalcare.dto.LoginRequest;
import com.animalcare.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        final UserDetails user = userService.loadUserByUsername(request.getUsername());
        if (user != null){
            Cookie cookie = new Cookie("token", jwtUtils.generateToken(user));
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

//    @PostMapping("/auth")
//    public String authenticate(@RequestBody LoginRequest request, Model model, HttpServletResponse response) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        final UserDetails user = userService.loadUserByUsername(request.getUsername());
//        if (user != null){
//            Cookie cookie = new Cookie("token", jwtUtils.generateToken(user));
//            cookie.setDomain("localhost");
//            cookie.setPath("/");
//            cookie.setMaxAge(3600);
//            response.addCookie(cookie);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + jwtUtils.generateToken(user));
//            String username = jwtUtils.extractUsername(jwtUtils.generateToken(user));
//            // Pass the user's profile to the template
//            model.addAttribute("user", username);
//            if(username.equals("admin"))
//                return "admin";
//            return "user";
//        }
//        return "login";
//    }
}