package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/signup")
    ResponseEntity<UserEntity> signup(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserDTO userDTO);
}
