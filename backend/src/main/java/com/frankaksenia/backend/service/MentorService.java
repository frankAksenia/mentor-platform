package com.frankaksenia.backend.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frankaksenia.backend.config.SecurityUtils;
import com.frankaksenia.backend.dto.MentorProfileCreateRequest;
import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.dto.MentorProfileUpdateRequest;
import com.frankaksenia.backend.exceptions.UnauthorisedActionException;
import com.frankaksenia.backend.mapper.MentorProfileResponseMapper;
import com.frankaksenia.backend.model.ERole;
import com.frankaksenia.backend.model.MentorProfile;
import com.frankaksenia.backend.model.Skill;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.repository.MentorProfileRepository;
import com.frankaksenia.backend.repository.UserRepository;



@Service
public class MentorService {

    private final UserRepository userRepository;
    private final MentorProfileRepository mentorProfileRepository;
    private final MentorProfileResponseMapper mentorProfileResponseMapper;

    public MentorService(UserRepository userRepository, MentorProfileRepository mentorProfileRepository, MentorProfileResponseMapper mentorProfileResponseMapper) {
        this.userRepository = userRepository;
        this.mentorProfileRepository = mentorProfileRepository;
        this.mentorProfileResponseMapper = mentorProfileResponseMapper;
    }

    public MentorProfileResponse createMentorProfile(MentorProfileCreateRequest request) {

        User mentor = getCurrentUser();

        if(mentor.getRole() != ERole.MENTOR) 
            throw new UnauthorisedActionException("Invalid User Role", "User with id " + mentor.getId() + " is not a mentor");

        MentorProfile mentorProfile = new MentorProfile();
        mentorProfile.setUser(mentor);
        mentorProfile.setTitle(request.title());
        mentorProfile.setBio(request.bio());
        mentorProfile.setHourlyRate(request.hourlyRate());
        mentorProfile.setYearsOfExperience(request.yearsOfExperience());
        mentorProfile.setLanguages(request.languages());
        mentorProfile.setSkills(request.skills());
        mentorProfile.setActive(true);

        mentorProfileRepository.save(mentorProfile);

        return mentorProfileResponseMapper.mapToMentorProfileResponse(mentorProfile);
    }


    private User getCurrentUser() {
        String username = SecurityUtils.getCurrentUserUsername();

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

@Transactional
public MentorProfileResponse updateMentorProfile(MentorProfileUpdateRequest request) {

    User mentor = getCurrentUser();

    if (mentor.getRole() != ERole.MENTOR) {
        throw new UnauthorisedActionException("Invalid User Role", 
            "User with id " + mentor.getId() + " is not a mentor");
    }

    // 1. Fetch the profile
    MentorProfile mentorProfile = mentorProfileRepository.findByUser(mentor)
        .orElseThrow(() -> new RuntimeException("Mentor profile not found for user with id: " + mentor.getId()));

    // 2. Update basic fields (using Object wrappers Double/Integer to allow nulls)
    if (request.title() != null) {
        mentorProfile.setTitle(request.title());
    }

    if (request.bio() != null) {
        mentorProfile.setBio(request.bio());
    }

    if (request.hourlyRate() != null) {
        mentorProfile.setHourlyRate(request.hourlyRate());
    }

    if (request.yearsOfExperience() != null) {
        mentorProfile.setYearsOfExperience(request.yearsOfExperience());
    }

    if (request.languages() != null) {
        mentorProfile.setLanguages(request.languages());
    }

    // 3. Update Skills safely to avoid ConcurrentModificationException
    if (request.skills() != null) {
        // Clear the managed collection instead of replacing the Set
        mentorProfile.getSkills().clear();
        mentorProfile.getSkills().addAll(request.skills());
    }

    // 4. Save changes
    mentorProfileRepository.save(mentorProfile);

    // 5. THE CRITICAL FIX: Force initialization while inside the @Transactional session
    // This prevents the "LazyInitializationException" during mapping
    Hibernate.initialize(mentorProfile.getSkills());

    // 6. Map to Response DTO
    return mentorProfileResponseMapper.mapToMentorProfileResponse(mentorProfile);
}
}
