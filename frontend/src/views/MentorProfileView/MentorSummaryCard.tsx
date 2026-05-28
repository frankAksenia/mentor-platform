import { IoIosStar } from "react-icons/io";
import { Card } from "../../components/Card/Card";
import type { MentorProfile } from "../../types/domain";
import { useState } from "react";

type MentorSummaryCardProps = {
  mentor: MentorProfile;
};

const MentorSummaryCard = ({ mentor }: MentorSummaryCardProps) => {
  const fullName = `${mentor.firstName} ${mentor.lastName}`;
  const [imageError, setImageError] = useState(false);

  return (
    <Card className="mentor-profile__summary-card">
      <div className="mentor-profile__avatar">
        {mentor.image && !imageError ? (
          <img
            src={mentor.image}
            alt={`Avatar for ${fullName}`}
            onError={() => setImageError(true)}
          />
        ) : (
          <div>
            {mentor.firstName?.[0]}
            {mentor.lastName?.[0]}
          </div>
        )}
      </div>

      <div className="mentor-profile__summary-content">
        <div className="mentor-profile__identity">
          <h1>{fullName}</h1>
          <p>{mentor.title}</p>
          <div className="mentor-profile__skill-list">
            {mentor.skills?.map((skill, index) => (
              <span key={skill.id ?? `${skill.name}-${index}`}>
                {skill.name}
              </span>
            ))}
          </div>
          <div className="mentor-profile__details">
            <div className="mentor-profile__detail">
              <h2 className="mentor-profile__detail-label">EXPERIENCE</h2>
              <p className="mentor-profile__detail-value">
                {mentor.yearsOfExperience} years
              </p>
            </div>
            <div className="line-divider" aria-hidden="true" />

            <div className="mentor-profile__detail">
              <h2 className="mentor-profile__detail-label">LANGUAGES</h2>
              <div className="mentor-profile__language-list">
                {mentor.languages.map((language, index) => (
                  <span
                    className="mentor-profile__detail-value"
                    key={`${language}-${index}`}
                  >
                    {language}
                  </span>
                ))}
              </div>
            </div>
          </div>
        </div>
        <div className="mentor-profile__rating">
          <span>
            <IoIosStar aria-hidden="true" />
            {mentor.averageRating.toFixed(1)} ({mentor.reviewsCount} reviews)
          </span>
        </div>
      </div>
    </Card>
  );
};

export default MentorSummaryCard;
