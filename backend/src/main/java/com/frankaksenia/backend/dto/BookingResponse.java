package com.frankaksenia.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.frankaksenia.backend.model.BookingStatus;

public record BookingResponse(
    UUID id,
    UUID mentorId,
    UUID studentId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    BookingStatus status,
    String topic,
    String message
) 
{}
