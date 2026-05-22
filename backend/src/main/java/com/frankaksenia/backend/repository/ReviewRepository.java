package com.frankaksenia.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByBooking_Mentor_Id(UUID mentorId);

}
