package com.frankaksenia.backend.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.frankaksenia.backend.model.MentorAvailability;

public interface MentorAvailabilityRepository extends JpaRepository<MentorAvailability, UUID> {
    @Query("""
        SELECT a
        FROM MentorAvailability a
        WHERE a.mentorProfile.id = :mentorId
          AND a.date = :date
          AND a.status = 'AVAILABLE'
        ORDER BY a.startTime ASC
    """)
    List<MentorAvailability> findAvailableTimeSlots(
        @Param("mentorId") UUID mentorId,
        @Param("date") LocalDate date
    );

}
