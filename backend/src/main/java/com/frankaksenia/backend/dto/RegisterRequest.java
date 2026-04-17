package com.frankaksenia.backend.dto;

import com.frankaksenia.backend.model.ERole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "First name is required")
    @Size(max=100, message = "First name must be less than 100 characters")
    String firstName,

    @NotBlank(message = "Last name is required")
    @Size(max=100, message = "Last name must be less than 100 characters")
    String lastName,

    @NotBlank(message = "Username is required")
    @Size(min=5, max=50, message = "Username must be between 5 and 50 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min=8, max=100, message = "Password must be between 8 and 100 characters")
    String password,

    @NotNull(message = "Role is required")
    ERole role
) 
{}
