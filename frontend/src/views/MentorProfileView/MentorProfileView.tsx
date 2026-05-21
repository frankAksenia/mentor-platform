import { Link, useLocation, useParams } from "react-router-dom";
import { ChevronLeft } from "lucide-react";
import { Card } from "../../components/Card/Card";
import "react-calendar/dist/Calendar.css";

import type { MentorPreview, AvailableSlot } from "../../types/domain";
import MentorBookingCard from "./MentorBookingCard";
import MentorSummaryCard from "./MentorSummaryCard";
import "./MentorProfileView.css";
import { SetStateAction, useState } from "react";
import Reviews from "./Reviews";

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
          <MentorSummaryCard mentor={mentor} />
          <Card className="mentor-profile__about-card">
            <h2>About</h2>
            <p>{mentor.bio}</p>
          </Card>
        </div>
        <div className="mentor-profile__sidebar">
          <MentorBookingCard
            price={mentor.price}
            selectedDate={selectedDate}
            selectedSlot={selectedSlot}
            slots={slots}
            canBook={Boolean(mentorId && selectedSlot)}
            onDateChange={(date: SetStateAction<Date>) => {
              setSelectedDate(date);
              setSelectedSlot(null);
            }}
            onSlotSelect={setSelectedSlot}
          />
        </div>
      </div>
      <Reviews />
    </main>
  );
};
