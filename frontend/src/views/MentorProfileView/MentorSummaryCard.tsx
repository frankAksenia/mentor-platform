import { IoIosStar } from "react-icons/io";
import { Card } from "../../components/Card/Card";
import type { MentorPreview } from "../../types/domain";

type MentorSummaryCardProps = {
  mentor: MentorPreview;
};

const MentorSummaryCard = ({ mentor }: MentorSummaryCardProps) => {
  const fullName = `${mentor.first_name} ${mentor.last_name}`;

  return (
    <Card className="mentor-profile__summary-card">
      <div className="mentor-profile__avatar">
        {mentor.image ? (
          <img src={mentor.image} alt={`Avatar for ${fullName}`} />
        ) : (
          <>
            {mentor.first_name.charAt(0)}
            {mentor.last_name.charAt(0)}
          </>
        )}
      </div>

      <div className="mentor-profile__summary-content">
        <div className="mentor-profile__identity">
          <h1>{fullName}</h1>
          <p>{mentor.title}</p>
          <div className="mentor-profile__skill-list">
            {mentor.skills.map((skill) => (
              <span key={skill}>{skill}</span>
            ))}
          </div>
          <div className="mentor-profile__details">
            <div className="mentor-profile__detail">
              <h2 className="mentor-profile__detail-label">EXPERIENCE</h2>
              <p className="mentor-profile__detail-value">
                {mentor.experience} years
              </p>
            </div>
            <div className="line-divider" aria-hidden="true" />

            <div className="mentor-profile__detail">
              <h2 className="mentor-profile__detail-label">LANGUAGES</h2>
              <div className="mentor-profile__language-list">
                {mentor.languages.map((language) => (
                  <span className="mentor-profile__detail-value" key={language}>
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
            {mentor.rating} ({mentor.num_reviews} reviews)
          </span>
        </div>
      </div>
    </Card>
  );
};

export default MentorSummaryCard;
