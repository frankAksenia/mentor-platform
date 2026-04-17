package com.frankaksenia.backend.dto;

import java.util.UUID;

import com.frankaksenia.backend.model.ERole;

public record AuthenticationResponse(
    String token,
    UUID userId,
    String firstName,
    String lastName,
    String username,
    String email,
    ERole role
) {

}
