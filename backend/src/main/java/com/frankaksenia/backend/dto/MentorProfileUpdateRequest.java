package com.frankaksenia.backend.dto;

import java.util.Set;

import com.frankaksenia.backend.model.Skill;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record MentorProfileUpdateRequest(

    @Size(max = 50, message = "Title must be at most 50 characters")
    String title,

    @Size(max = 300, message = "Bio must be at most 300 characters")
    String bio,

    @Positive(message = "Hourly rate must be a positive number")
    Double hourlyRate,

    @Positive(message = "Year of experience must be a positive number")
    Integer yearsOfExperience,

    Set<String> languages,

    Set<Skill> skills
)
{}
