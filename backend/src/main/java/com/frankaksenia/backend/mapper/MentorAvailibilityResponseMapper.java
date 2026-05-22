package com.frankaksenia.backend.mapper;

import org.springframework.stereotype.Component;

import com.frankaksenia.backend.dto.MentorAvailabilityResponse;
import com.frankaksenia.backend.model.MentorAvailability;

@Component
public class MentorAvailibilityResponseMapper {

    public MentorAvailabilityResponse mapToMentorAvailabilityResponse(MentorAvailability mentorAvailability) {
        return new MentorAvailabilityResponse(mentorAvailability.getStartTime(), mentorAvailability.getEndTime());
    }

}
