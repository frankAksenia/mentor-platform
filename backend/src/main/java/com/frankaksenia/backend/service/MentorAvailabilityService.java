package com.frankaksenia.backend.service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.MentorAvailabilityResponse;
import com.frankaksenia.backend.mapper.MentorAvailibilityResponseMapper;
import com.frankaksenia.backend.repository.MentorAvailabilityRepository;
import com.frankaksenia.backend.repository.MentorProfileRepository;

@Service
public class MentorAvailabilityService {

    private final MentorAvailabilityRepository mentorAvailabilityRepository;


    private final MentorAvailibilityResponseMapper mentorAvailibilityResponseMapper;

    public MentorAvailabilityService(MentorAvailabilityRepository mentorAvailabilityRepository, MentorProfileRepository mentorProfileRepository) {
        this.mentorAvailabilityRepository = mentorAvailabilityRepository;
        this.mentorAvailibilityResponseMapper = new MentorAvailibilityResponseMapper();
    }

    public List<MentorAvailabilityResponse> getMentorAvailability(UUID mentorId, LocalDate date) {
    return mentorAvailabilityRepository.findAvailableTimeSlots(mentorId, date).stream()
        .map(mentorAvailibilityResponseMapper::mapToMentorAvailabilityResponse)
        .toList();
    }

}
