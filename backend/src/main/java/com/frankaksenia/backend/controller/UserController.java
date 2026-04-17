package com.frankaksenia.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.AuthenticationResponse;
import com.frankaksenia.backend.dto.RegisterRequest;
import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    } 

    @GetMapping("/users")
    @ResponseBody
    public List<UserResponse> getUsers() {
     return userService.getAllUsers();
    }
    
    // @PostMapping("/register")
    // public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
    //     AuthenticationResponse authenticationResponse = userService.registerUser(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    // }
    
}
