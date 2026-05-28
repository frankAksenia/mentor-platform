package com.frankaksenia.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frankaksenia.backend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByMentorProfile_Id(UUID mentorProfileId);

    @Query("""
    select avg(r.rating)
    from Review r
    where r.mentorProfile.id = :mentorId
    """)
    Double findAverageRatingByMentorId(UUID mentorId);

    @Query("""
    select count(r)
    from Review r
    where r.mentorProfile.id = :mentorId
    """)
    Long countReviewsByMentorId(UUID mentorId);

}
