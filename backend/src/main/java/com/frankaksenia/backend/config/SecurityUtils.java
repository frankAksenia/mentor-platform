package com.frankaksenia.backend.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.frankaksenia.backend.exceptions.UnauthorisedActionException;

@Component
public class SecurityUtils {

    public static String getCurrentUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorisedActionException("Unauthorised", "User is not authenticated");
        }
        return authentication.getName();
    }
}
