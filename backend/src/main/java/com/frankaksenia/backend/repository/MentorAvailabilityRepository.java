package com.frankaksenia.backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.ESlotStatus;
import com.frankaksenia.backend.model.MentorAvailability;

public interface MentorAvailabilityRepository extends JpaRepository<MentorAvailability, UUID> {

    List<MentorAvailability> findByMentorProfile_IdAndDateAndStatusOrderByStartTimeAsc(
        UUID mentorId,
        LocalDate date,
        ESlotStatus status
    );

}
