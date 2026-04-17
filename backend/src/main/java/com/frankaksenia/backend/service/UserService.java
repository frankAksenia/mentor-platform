package com.frankaksenia.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.AuthenticationResponse;
import com.frankaksenia.backend.dto.RegisterRequest;
import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(user -> new UserResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getRole().toString()
                    ))
                    .toList();
    }

}
