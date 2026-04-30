package com.frankaksenia.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookingCreateRequest(
    @NotNull(message = "Mentor ID is required")
    UUID mentorId,

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    LocalDateTime startTime,

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    LocalDateTime endTime,

    @Size(max = 200, message = "Topic must be at most 200 characters")
    String topic,
    
    @Size(max = 2000, message = "Message must be at most 2000 characters")
    String message
) {}
