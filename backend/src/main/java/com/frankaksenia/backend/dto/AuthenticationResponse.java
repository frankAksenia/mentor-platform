package com.frankaksenia.backend.dto;

import java.util.UUID;

import com.frankaksenia.backend.model.Role;

public record AuthenticationResponse(
    String token,
    UUID userId,
    String firstName,
    String lastName,
    String email,
    Role role
) {

}
