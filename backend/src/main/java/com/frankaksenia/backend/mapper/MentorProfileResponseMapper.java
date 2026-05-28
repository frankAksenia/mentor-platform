package com.frankaksenia.backend.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.model.MentorProfile;

@Component
public class MentorProfileResponseMapper {

    private final SkillResponseMapper skillResponseMapper;

    public MentorProfileResponseMapper(SkillResponseMapper skillResponseMapper) {
        this.skillResponseMapper = skillResponseMapper;
    }

    public MentorProfileResponse mapToMentorProfileResponse(MentorProfile mentorProfile) {
        return new MentorProfileResponse(
            mentorProfile.getUser().getId(),
            mentorProfile.getId(),
            mentorProfile.getUser().getFirstName(),
            mentorProfile.getUser().getLastName(),
            mentorProfile.getTitle(),
            mentorProfile.getBio(),
            mentorProfile.getHourlyRate(),
            mentorProfile.getAverageRating(),
            mentorProfile.getReviewsCount(),
            mentorProfile.getYearsOfExperience(),           
            mentorProfile.getSkills().stream()
                .map(skillResponseMapper::mapToSkillResponse)
                .collect(Collectors.toSet()),
            mentorProfile.getLanguages()
        ); 
    }
}