package com.frankaksenia.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Size(max=100, message = "First name must be less than 100 characters")
    String firstName,

    @Size(max=100, message = "Last name must be less than 100 characters")
    String lastName,

    @Size(min=5, max=50, message = "Username must be between 5 and 50 characters")
    String username,

    @Email(message = "Email should be valid")
    String email
) 
{}
