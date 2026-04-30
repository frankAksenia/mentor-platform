package com.frankaksenia.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



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
     List<UserResponse> users = userService.getAllUsers();

     return ResponseEntity.ok(users);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> getMyUser() {
        UserResponse user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }
    
}
