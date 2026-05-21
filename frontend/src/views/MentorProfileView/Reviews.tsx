import { Card } from "../../components/Card/Card";
import type { Review } from "../../types/domain";

type ReviewPreview = Pick<Review, "id" | "rating" | "comment" | "createdAt"> & {
  student: {
    firstName: string;
    lastName: string;
  };
};

const placeholderReviews: ReviewPreview[] = [
  {
    id: "1",
    student: { firstName: "Emma", lastName: "Müller" },
    rating: 5,
    comment:
      "Excellent mentor. Explained everything clearly and gave practical examples.",
    createdAt: "21 May 2026",
  },
  {
    id: "2",
    student: { firstName: "Lukas", lastName: "Schmidt" },
    rating: 4,
    comment:
      "Very helpful session. I understood the topic much better afterwards.",
    createdAt: "20 May 2026",
  },
  {
    id: "3",
    student: { firstName: "Mia", lastName: "Fischer" },
    rating: 5,
    comment: "Amazing experience. The mentor was patient and well prepared.",
    createdAt: "18 May 2026",
  },
  {
    id: "4",
    student: { firstName: "Noah", lastName: "Wagner" },
    rating: 4,
    comment: "Good explanations and friendly attitude. Would book again.",
    createdAt: "15 May 2026",
  },
  {
    id: "5",
    student: { firstName: "Anna", lastName: "Hoffmann" },
    rating: 3,
    comment:
      "Useful session, but I wish there had been more time for questions.",
    createdAt: "12 May 2026",
  },
];

const Reviews = () => {
  return (
    <div className="mentor-profile__reviews">
      <h2>Student reviews</h2>
      <div className="mentor-profile__review-list">
        {placeholderReviews.map((review) => (
          <Card as="article" className="mentor-profile__review" key={review.id}>
            <div className="mentor-profile__review-header">
              <h3>
                {review.student.firstName} {review.student.lastName}
              </h3>
              <span>{review.createdAt}</span>
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
