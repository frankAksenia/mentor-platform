package com.frankaksenia.backend.mapper;

import com.frankaksenia.backend.dto.SkillResponse;
import com.frankaksenia.backend.model.Skill;
import org.springframework.stereotype.Component;


@Component
public class SkillResponseMapper {

    public SkillResponse mapToSkillResponse(Skill skill) {
    return new SkillResponse(
        skill.getId(),
        skill.getName(),
        skill.getCategory()
    );
}
}
