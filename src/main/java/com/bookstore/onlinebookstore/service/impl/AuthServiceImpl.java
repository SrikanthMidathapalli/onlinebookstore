package com.bookstore.onlinebookstore.service.impl;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import com.bookstore.onlinebookstore.repository.UserRepository;
import com.bookstore.onlinebookstore.service.AuthService;
import com.bookstore.onlinebookstore.util.JwtTokenUtil;  // Assuming you have this utility class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;  // Utility class for generating JWT tokens

    @Override
    public UserEntity signup(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());  // Ensure this line sets the role from the DTO
        return userRepository.save(user);
    }


    @Override
    public String login(UserDTO userDTO) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                // Generate JWT token
                return jwtTokenUtil.generateToken(user);
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

}
