package com.frankaksenia.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.UpdateUserRequest;
import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<UserResponse>> getUsers() {
     List<UserResponse> usersResponse = userService.getAllUsers();
     return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> getMyUser() {
        UserResponse userResponse = userService.getUserInfo();
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserResponse> updateMyUser(@Valid @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.updateUserInfo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    
}
