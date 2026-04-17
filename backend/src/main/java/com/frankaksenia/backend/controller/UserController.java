package com.frankaksenia.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.service.UserService;

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
    public List<UserResponse> getUsers() {
     return userService.getAllUsers();
    }
    
}
