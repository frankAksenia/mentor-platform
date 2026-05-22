package com.frankaksenia.backend.dto;

import java.time.LocalTime;

public record MentorAvailabilityResponse(LocalTime startTime, LocalTime endTime) 
    {}
