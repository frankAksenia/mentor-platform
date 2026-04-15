package com.frankaksenia.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.MentorProfile;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, UUID> {

}
