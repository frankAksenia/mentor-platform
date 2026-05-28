package com.frankaksenia.backend.dto;

import java.util.Set;
import java.util.UUID;

public record MentorProfileResponse(
    UUID userId,
    UUID mentorProfileId,
    String firstName,
    String lastName,
    String title, 
    String bio,
    double hourlyRate,
    double averageRating,
    int reviewsCount,
    int yearOfExperience,
    Set<SkillResponse> skills,
    Set<String> languages
) 
{}
