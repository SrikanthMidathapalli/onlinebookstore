package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthControllerImpl {

    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";  // This will map to src/main/resources/templates/signup.html
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";  // This will map to src/main/resources/templates/login.html
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO, Model model) {
        UserEntity user = authService.signup(userDTO);
        model.addAttribute("user", user);
        return "redirect:/auth/login";  // Redirect after signup
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, Model model) {
        String token = authService.login(userDTO);
        model.addAttribute("token", token);
        return "redirect:/home";  // Redirect after login
    }
}
