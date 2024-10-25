package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;

public interface AuthService {
    UserEntity signup(UserDTO userDTO);
    String login(UserDTO userDTO);
}
