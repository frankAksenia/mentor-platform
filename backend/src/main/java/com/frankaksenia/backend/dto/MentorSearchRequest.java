package com.frankaksenia.backend.dto;

import java.util.List;

public record MentorSearchRequest(
    String search,
    List<String> skills,
    List<String> languages,
    Integer minPrice,
    Integer maxPrice,
    String sortBy,
    String direction
) 
{}
