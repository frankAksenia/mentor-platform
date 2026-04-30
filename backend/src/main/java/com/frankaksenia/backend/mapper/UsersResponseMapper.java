package com.frankaksenia.backend.mapper;

import org.springframework.stereotype.Component;

import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.model.User;

@Component
public class UsersResponseMapper {

    public UserResponse mapToUsersResponse(User user) {
        return new UserResponse(
            user.getId(), 
            user.getFirstName(), 
            user.getLastName(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getRole().toString());
    }
}
