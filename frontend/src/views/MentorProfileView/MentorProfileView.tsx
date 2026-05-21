import { Link, useLocation, useParams } from "react-router-dom";
import { ChevronLeft } from "lucide-react";
import { IoIosStar } from "react-icons/io";
import { Button } from "../../components/Button/Button";
import { Card } from "../../components/Card/Card";
import { Calendar } from "react-calendar";
import "react-calendar/dist/Calendar.css";

import type { MentorPreview, AvailableSlot } from "../../types/domain";
import "./MentorProfileView.css";
import { useState } from "react";

type MentorProfileState = {
  mentor?: Partial<MentorPreview>;
};

const fallbackMentor: MentorPreview = {
  title: "Mentor profile",
  image:
    "https://lh3.googleusercontent.com/aida-public/AB6AXuAWnWViImVlhZOqEG0XvqJN6NnV2jo6pZcxRPX-K4iCKMh_HjtN4p3oPJlKg1LVu-BAiWstu_7w8JuuMdbd_xg2mOjkn7AIZgdNyr83QFws9MSf-aACmdFEFwv2taHXo2WJHinsRU-K6SY65UjaYm8dsdbtkLlEkFw5dd2kM4h-Shnq2RSMWbNOBbQuXgoDxr--VVF1XhdPdbVLIPlZuFjVBHDa1t22viWhK0XfPiejQW-OOeMCJki5g-PjIWiJzqL7Akulyi5gD4o",
  first_name: "Mentor",
  last_name: "Name",
  price: 90,
  rating: 4.8,
  bio: "This mentor profile template will show the mentor bio, skills, rating, price, availability, and booking actions once the backend data is connected.",
  num_reviews: 0,
  skills: ["Expertise", "Mentorship", "Career Growth"],
  experience: 12,
  languages: ["English", "German", "Russian"],
};

const mockSlotHours = [9, 10, 11, 14, 15, 16];

const toLocalDateParam = (date: Date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

const createDateTime = (date: Date, hour: number) => {
  return `${toLocalDateParam(date)}T${String(hour).padStart(2, "0")}:00:00`;
};

const getMockAvailableSlots = (date: Date): AvailableSlot[] => {
  const isWeekend = date.getDay() === 0 || date.getDay() === 6;

  if (isWeekend) {
    return [];
  }

  return mockSlotHours.map((hour) => ({
    startTime: createDateTime(date, hour),
    endTime: createDateTime(date, hour + 1),
  }));
};

export const MentorProfileView = () => {
  const { mentorId } = useParams();
  const location = useLocation();
  const routeMentor = (location.state as MentorProfileState | null)?.mentor;
  const mentor: MentorPreview = {
    ...fallbackMentor,
    ...routeMentor,
    skills: routeMentor?.skills ?? fallbackMentor.skills,
    languages: routeMentor?.languages ?? fallbackMentor.languages,
  };
  const fullName = `${mentor.first_name} ${mentor.last_name}`;
  const [selectedDate, setSelectedDate] = useState<Date>(new Date());
  const [selectedSlot, setSelectedSlot] = useState<AvailableSlot | null>(null);
  const slots = getMockAvailableSlots(selectedDate);

  return (
    <main>
      <Link className="mentor-profile__back-link" to="/mentors">
        <ChevronLeft size={20} />
        Back to mentors
      </Link>
      <div className="mentor-profile__content">
        <div className="mentor-profile__main">
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
                        <span
                          className="mentor-profile__detail-value"
                          key={language}
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
                  {mentor.rating} ({mentor.num_reviews} reviews)
                </span>
              </div>
            </div>
          </Card>
          <Card className="mentor-profile__about-card">
            <h2>About</h2>
            <p>{mentor.bio}</p>
          </Card>
        </div>
        <div className="mentor-profile__sidebar">
          <Card className="mentor-profile__booking-card">
            <div className="mentor-profile__booking-header">
              <p className="mentor-profile__booking-title">Book a session</p>
              <div className="mentor-profile__price-group">
                <p className="mentor-profile__price">
                  {"\u20AC"} {mentor.price}
                </p>
                <p className="mentor-profile__price-unit">per hour</p>
              </div>
            </div>
            <p className="mentor-profile__select-date-label">Select date</p>
            <Calendar
              onChange={(date) => {
                if (date instanceof Date) {
                  setSelectedDate(date);
                  setSelectedSlot(null);
                }
              }}
              value={selectedDate}
            />
            <div className="mentor-profile__booking-availability">
              <p className="mentor-profile__availability-title">
                Available time slots
              </p>

              {slots.length === 0 ? (
                <p>No slots available for this date.</p>
              ) : (
                <div className="mentor-profile__slot-list">
                  {slots.map((slot) => (
                    <button
                      key={slot.startTime}
                      type="button"
                      className={
                        selectedSlot?.startTime === slot.startTime
                          ? "is-selected"
                          : ""
                      }
                      onClick={() => setSelectedSlot(slot)}
                    >
                      {new Date(slot.startTime).toLocaleTimeString([], {
                        hour: "2-digit",
                        minute: "2-digit",
                      })}
                    </button>
                  ))}
                </div>
              )}
            </div>
            <Button
              className="mentor-profile__booking-button"
              type="button"
              disabled={!mentorId || !selectedSlot}
            >
              Confirm and book session
            </Button>
          </Card>
        </div>
      </div>
    </main>
  );
};
