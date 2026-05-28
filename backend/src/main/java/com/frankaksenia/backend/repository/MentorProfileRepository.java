package com.frankaksenia.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frankaksenia.backend.model.MentorProfile;
import com.frankaksenia.backend.model.User;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, UUID> {

    Optional<MentorProfile> findByUser(User user);

    @Query("""
    select distinct m
    from MentorProfile m
    left join fetch m.skills
    """)
    List<MentorProfile> findAllMentors();

	}
