package com.frankaksenia.backend.dto;

import java.util.Set;
import java.util.UUID;

public record MentorProfileResponse(
    UUID userId,
    UUID mentorProfileId,
    String title,
    String bio,
    double hourlyRate,
    int yearOfExperience,
    Set<String> languages,
    Set<SkillResponse> skills
) 
{}
