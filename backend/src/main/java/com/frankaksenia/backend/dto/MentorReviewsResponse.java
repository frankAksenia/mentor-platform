package com.frankaksenia.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MentorReviewsResponse(UUID reviewId, String reviewerFirstName, String reviewerLastName, float rating, String comment, LocalDateTime createdAt) 
{}
