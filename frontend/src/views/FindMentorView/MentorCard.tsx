import { IoIosStar } from "react-icons/io";
import { Link } from "react-router-dom";
import { Card } from "../../components/Card/Card";
import type { MentorProfile } from "../../types/domain";
import { useState } from "react";

type MentorCardProps = {
  mentor: MentorProfile;
  profilePath: string;
};

const MentorCard = ({ mentor, profilePath }: MentorCardProps) => {
  const [imageError, setImageError] = useState(false);

  return (
    <Card as="article" className="mentor-card">
      <div className="mentor-card__header">
        <div className="mentor-card__avatar">
          {mentor.image && !imageError ? (
            <img
              src={mentor.image}
              alt={`Avatar for ${mentor.firstName} ${mentor.lastName}`}
              onError={() => setImageError(true)}
            />
          ) : (
            <div>
              {mentor.firstName?.[0]}
              {mentor.lastName?.[0]}
            </div>
          )}
        </div>
        <div className="mentor-card__meta">
          <p className="mentor-card__rating">
            <IoIosStar />
            {mentor.averageRating.toFixed(1)} ({mentor.reviewsCount})
          </p>
          <p className="mentor-card__price">
            From {"\u20AC"}
            {mentor.hourlyRate}/hr
          </p>
        </div>
      </div>
      <div className="mentor-card__content">
        <h2 className="mentor-card__name">
          {mentor.firstName} {mentor.lastName}
        </h2>
        <h3 className="mentor-card__title">{mentor.title}</h3>
        <p className="mentor-card__bio">{mentor.bio}</p>
      </div>
      <Link
        className="mentor-card__profile-link"
        to={profilePath}
        state={{ mentor }}
      >
        View profile
      </Link>
    </Card>
  );
};

export default MentorCard;
