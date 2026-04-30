package com.frankaksenia.backend.dto;

import com.frankaksenia.backend.model.ECategory;

public record SkillRequest(
    String name,
    ECategory category
) 
{}
