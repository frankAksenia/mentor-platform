package com.frankaksenia.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.SkillRequest;
import com.frankaksenia.backend.dto.SkillResponse;
import com.frankaksenia.backend.mapper.SkillResponseMapper;
import com.frankaksenia.backend.model.Skill;
import com.frankaksenia.backend.repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    private final SkillResponseMapper skillResponseMapper;

    public SkillService(SkillRepository skillRepository, SkillResponseMapper skillResponseMapper) {
        this.skillRepository = skillRepository;
        this.skillResponseMapper = skillResponseMapper;
    }

    public List<SkillResponse> getSkills() {
        return skillRepository.findAll().stream()
                .map(skillResponseMapper::mapToSkillResponse)
                .toList();
    }

    public SkillResponse createSkill(SkillRequest skillResponse) {

        Skill skill = new Skill();
        skill.setName(skillResponse.name());
        skill.setCategory(skillResponse.category());
        skillRepository.save(skill);
        return skillResponseMapper.mapToSkillResponse(skill);
    }

}
