package com.frankaksenia.backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frankaksenia.backend.config.SecurityUtils;
import com.frankaksenia.backend.dto.MentorProfileCreateRequest;
import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.dto.MentorProfileUpdateRequest;
import com.frankaksenia.backend.dto.MentorReviewsResponse;
import com.frankaksenia.backend.exceptions.ResourceNotFoundException;
import com.frankaksenia.backend.exceptions.UnauthorisedActionException;
import com.frankaksenia.backend.mapper.MentorProfileResponseMapper;
import com.frankaksenia.backend.mapper.MentorReviewsResponseMapper;
import com.frankaksenia.backend.model.ERole;
import com.frankaksenia.backend.model.MentorProfile;
import com.frankaksenia.backend.model.Review;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.repository.MentorProfileRepository;
import com.frankaksenia.backend.repository.ReviewRepository;
import com.frankaksenia.backend.repository.UserRepository;



@Service
public class MentorProfileService {

    private final UserRepository userRepository;
    private final MentorProfileRepository mentorProfileRepository;
    private final ReviewRepository reviewRepository;
    private final MentorProfileResponseMapper mentorProfileResponseMapper;
    private final MentorReviewsResponseMapper mentorReviewsResponseMapper;

    public MentorProfileService(UserRepository userRepository, MentorProfileRepository mentorProfileRepository, ReviewRepository reviewRepository, MentorProfileResponseMapper mentorProfileResponseMapper) {
        this.userRepository = userRepository;
        this.mentorProfileRepository = mentorProfileRepository;
        this.reviewRepository = reviewRepository;
        this.mentorProfileResponseMapper = mentorProfileResponseMapper;
        this.mentorReviewsResponseMapper = new MentorReviewsResponseMapper();
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

    MentorProfile mentorProfile = mentorProfileRepository.findByUser(mentor)
        .orElseThrow(() -> new RuntimeException("Mentor profile not found for user with id: " + mentor.getId()));

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

    if (request.skills() != null) {
        mentorProfile.getSkills().clear();
        mentorProfile.getSkills().addAll(request.skills());
    }

    mentorProfileRepository.save(mentorProfile);

    Hibernate.initialize(mentorProfile.getSkills());

    return mentorProfileResponseMapper.mapToMentorProfileResponse(mentorProfile);
    }

public MentorProfileResponse getMentorProfile(UUID mentorId) {
    MentorProfile mentorProfile = mentorProfileRepository.findById(mentorId)
        .orElseThrow(() -> new ResourceNotFoundException("Mentor profile", "Mentor profile with id: " + mentorId + " not found"));
    return mentorProfileResponseMapper.mapToMentorProfileResponse(mentorProfile);
    }

public List<MentorReviewsResponse> getMentorReviews(UUID mentorId) {
    UUID mentorUserId = mentorProfileRepository.findById(mentorId)
        .map(mentorProfile -> mentorProfile.getUser().getId())
        .orElse(mentorId);

    List<Review> reviews = reviewRepository.findByBooking_Mentor_Id(mentorUserId);    
    return reviews.stream()
        .map(mentorReviewsResponseMapper::mapToMentorReviewsResponse)
        .toList();
    }

// @Transactional(readOnly = true)
public List<MentorProfileResponse> getAllMentors() {
    List<MentorProfile> mentorProfiles = mentorProfileRepository.findAllMentors();
    return mentorProfiles.stream()
        .map(mentorProfileResponseMapper::mapToMentorProfileResponse)
        .toList();
}

}   
