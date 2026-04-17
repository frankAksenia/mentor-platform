package com.frankaksenia.backend.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.AuthenticationResponse;
import com.frankaksenia.backend.dto.LoginRequest;
import com.frankaksenia.backend.dto.RegisterRequest;
import com.frankaksenia.backend.exceptions.UserAlreadyExistsException;
import com.frankaksenia.backend.mapper.AuthenticationResponseMapper;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.repository.UserRepository;



@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationResponseMapper authenticationResponseMapper;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationResponseMapper authenticationResponseMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    public AuthenticationResponse registerUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException("Registration Failed", "An account with email " + request.email() + " already exists.");
    }
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user.setCreatedAt(LocalDateTime.now());

        User newUser = userRepository.save(user);

        return authenticationResponseMapper.mapToAuthenticationResponse(newUser);

    }

    public AuthenticationResponse loginUser(LoginRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

    // private final UserService userService;
    // private final JwtService jwtService;


}
