package com.frankaksenia.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.Booking;
import com.frankaksenia.backend.model.BookingStatus;
import com.frankaksenia.backend.model.User;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByStudentId(UUID studentId);

    List<Booking> findByMentorId(UUID mentorId); 

    boolean existsByMentorAndStatusInAndStartTimeLessThanAndEndTimeGreaterThan(User mentor, List<BookingStatus> statuses, LocalDateTime end, LocalDateTime start);
    
    List<Booking> findByMentorIdAndStartTimeLessThanAndEndTimeGreaterThan(UUID mentorId, LocalDateTime end, LocalDateTime start);

}
