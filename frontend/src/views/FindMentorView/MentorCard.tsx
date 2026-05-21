import { IoIosStar } from "react-icons/io";
import { Link } from "react-router-dom";
import { Card } from "../../components/Card/Card";
import type { MentorPreview } from "../../types/domain";

type MentorCardProps = {
  mentor: MentorPreview;
  profilePath: string;
};

export const MentorCard = ({ mentor, profilePath }: MentorCardProps) => {
  return (
    <Card as="article" className="mentor-card">
      <div className="mentor-card__header">
        <img
          className="mentor-card__avatar"
          src={mentor.image}
          alt={mentor.title}
        />
        <div className="mentor-card__meta">
          <p className="mentor-card__rating">
            <IoIosStar />
            {mentor.rating} ({mentor.num_reviews})
          </p>
          <p className="mentor-card__price">
            From {"\u20AC"}
            {mentor.price}/hr
          </p>
        </div>
      </div>
      <div className="mentor-card__content">
        <h2 className="mentor-card__name">
          {mentor.first_name} {mentor.last_name}
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
