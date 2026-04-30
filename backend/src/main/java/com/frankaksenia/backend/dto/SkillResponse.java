package com.frankaksenia.backend.dto;

import java.util.UUID;

import com.frankaksenia.backend.model.ECategory;

public record SkillResponse(
    UUID id,
    String name,
    ECategory category
) 
{}