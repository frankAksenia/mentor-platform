package com.frankaksenia.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frankaksenia.backend.config.SecurityUtils;
import com.frankaksenia.backend.dto.UpdateUserRequest;
import com.frankaksenia.backend.dto.UserResponse;
import com.frankaksenia.backend.mapper.UsersResponseMapper;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.repository.UserRepository;



@Service
public class UserService {

    private final UserRepository userRepository;

    private final UsersResponseMapper usersResponseMapper;

    public UserService(UserRepository userRepository, UsersResponseMapper usersResponseMapper) {
        this.userRepository = userRepository;
        this.usersResponseMapper = usersResponseMapper;
    }

    public List<UserResponse> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(usersResponseMapper::mapToUsersResponse)
                    .toList();
    }

    public UserResponse getUserInfo() {
        User user = getCurrentUser();
        return usersResponseMapper.mapToUsersResponse(user);
    }

    public UserResponse updateUserInfo(UpdateUserRequest request) {
        User user = getCurrentUser();

        if(request.firstName() != null) {
            user.setFirstName(request.firstName());
        }

        if(request.lastName() != null) {
            user.setLastName(request.lastName());
        }

        if(request.email() != null) {
            user.setEmail(request.email());
        }

        if(request.username() != null) {
            user.setUsername(request.username());
        }

        userRepository.save(user);

        return usersResponseMapper.mapToUsersResponse(user);
    }

    private User getCurrentUser() {
        String username = SecurityUtils.getCurrentUserUsername();

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
