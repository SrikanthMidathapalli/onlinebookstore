package com.bookstore.onlinebookstore.controller.impl;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthControllerImpl {

    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";  // Returns signup.jsp
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";  // Returns login.jsp
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO, Model model) {
        UserEntity user = authService.signup(userDTO);
        model.addAttribute("user", user);
        return "redirect:/api/auth/login";  // Redirect to login page after signup
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, Model model) {
        String token = authService.login(userDTO);
        model.addAttribute("token", token);
        return "redirect:/home";  // Redirect to home or another page after login
    }
}
