import { Link, useLocation, useParams } from "react-router-dom";
import { ChevronLeft } from "lucide-react";
import { Card } from "../../components/Card/Card";
import "react-calendar/dist/Calendar.css";

import type { MentorProfile, AvailableSlot } from "../../types/domain";
import MentorBookingCard from "./MentorBookingCard";
import MentorSummaryCard from "./MentorSummaryCard";
import "./MentorProfileView.css";
import { useState } from "react";
import Reviews from "./Reviews";
import { useQuery } from "@tanstack/react-query";
import { fetchMentorAvailability } from "../../api/mentorApi";

type MentorProfileState = {
  mentor?: Partial<MentorProfile>;
};

const toLocalDateString = (date: Date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

export const MentorProfileView = () => {
  const { mentorId } = useParams();
  const location = useLocation();
  const routeMentor = (location.state as MentorProfileState | null)?.mentor;
  const mentor = (routeMentor ?? {}) as MentorProfile;
  const [selectedDate, setSelectedDate] = useState<Date>(new Date());
  const [selectedSlot, setSelectedSlot] = useState<AvailableSlot | null>(null);
  const selectedDateParam = toLocalDateString(selectedDate);

  const { data: slots = [] } = useQuery({
    queryKey: ["mentorAvailability", mentorId, selectedDateParam],
    queryFn: () => fetchMentorAvailability(mentorId!, selectedDateParam),
    enabled: Boolean(mentorId),
  });

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
            price={mentor.hourlyRate}
            selectedDate={selectedDate}
            selectedSlot={selectedSlot}
            slots={slots}
            canBook={Boolean(mentorId && selectedSlot)}
            onDateChange={(date) => {
              setSelectedDate(date);
              setSelectedSlot(null);
            }}
            onSlotSelect={setSelectedSlot}
          />
        </div>
      </div>
      {mentorId && <Reviews mentorId={mentorId} />}
    </main>
  );
};
