package com.frankaksenia.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.AuthenticationResponse;
import com.frankaksenia.backend.dto.RegisterRequest;
import com.frankaksenia.backend.mapper.AuthenticationResponseMapper;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.repository.UserRepository;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationResponseMapper authenticationResponseMapper;

    public AuthenticationService(UserRepository userRepository, AuthenticationResponseMapper authenticationResponseMapper) {
        this.userRepository = userRepository;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    public AuthenticationResponse registerUser(RegisterRequest request) {

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(request.role());
        user.setCreatedAt(LocalDateTime.now());

        User newUser = userRepository.save(user);

        return authenticationResponseMapper.mapToAuthenticationResponse(newUser);

    }

    // private final UserService userService;
    // private final JwtService jwtService;


}
