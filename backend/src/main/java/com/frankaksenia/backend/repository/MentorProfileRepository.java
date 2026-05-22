package com.frankaksenia.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.MentorProfile;
import com.frankaksenia.backend.model.User;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, UUID> {

    Optional<MentorProfile> findByUser(User user);

	}
