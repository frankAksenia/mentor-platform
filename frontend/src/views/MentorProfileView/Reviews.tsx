import { useQuery } from "@tanstack/react-query";
import { fetchReviewsForMentor } from "../../api/reviewApi";
import { Card } from "../../components/Card/Card";
import type { Review } from "../../types/domain";

type ReviewsProps = {
  mentorId: string;
};

const Reviews = ({ mentorId }: ReviewsProps) => {
  const { data: reviews = [] } = useQuery({
    queryKey: ["mentorReviews"],
    queryFn: () => fetchReviewsForMentor(mentorId),
  });

  const createdAt = reviews.length > 0 ? new Date(reviews[0].createdAt) : null;

  return (
    <div className="mentor-profile__reviews">
      <h2>Student reviews</h2>
      <div className="mentor-profile__review-list">
        {reviews.map((review) => (
          <Card
            as="article"
            className="mentor-profile__review"
            key={review.reviewId}
          >
            <div className="mentor-profile__review-header">
              <h3>
                {review.reviewerFirstName} {review.reviewerLastName}
              </h3>
              <span>{createdAt?.toLocaleString()}</span>
            </div>
            <p className="mentor-profile__review-rating">
              {"★".repeat(review.rating)}
            </p>
            <p>{review.comment}</p>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default Reviews;
