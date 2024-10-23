package com.bookstore.onlinebookstore.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String name;
    private String biography;
}