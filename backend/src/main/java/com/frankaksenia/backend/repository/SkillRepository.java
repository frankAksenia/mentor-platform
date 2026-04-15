package com.frankaksenia.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

}
