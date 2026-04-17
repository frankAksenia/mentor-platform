package com.frankaksenia.backend.mapper;

import org.springframework.stereotype.Component;

import com.frankaksenia.backend.dto.AuthenticationResponse;
import com.frankaksenia.backend.model.User;

@Component
public class AuthenticationResponseMapper {

    public AuthenticationResponse mapToAuthenticationResponse(User user) {
        return new AuthenticationResponse(
            "token", // Placeholder for the actual token generation logic
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }

}
