package com.frankaksenia.backend.mapper;

import com.frankaksenia.backend.dto.MentorReviewsResponse;
import com.frankaksenia.backend.model.Review;

public class MentorReviewsResponseMapper {

    public MentorReviewsResponse mapToMentorReviewsResponse(Review review) {
        return new MentorReviewsResponse(
            review.getBooking().getStudent().getFirstName(),
            review.getBooking().getStudent().getLastName(),
            review.getRating(),
            review.getComment(),
            review.getCreatedAt()
        );
    }
}
