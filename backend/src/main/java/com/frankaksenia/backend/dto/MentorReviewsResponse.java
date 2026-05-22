package com.frankaksenia.backend.dto;

import java.time.LocalDateTime;

public record MentorReviewsResponse(String reviewer_first_name, String reviewer_last_name, float rating, String comment, LocalDateTime created_at) 
{}
