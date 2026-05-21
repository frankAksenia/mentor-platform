import { Calendar } from "react-calendar";
import { Button } from "../../components/Button/Button";
import { Card } from "../../components/Card/Card";
import type { AvailableSlot, MentorBookingCardProps } from "../../types/domain";

const MentorBookingCard = ({
  price,
  selectedDate,
  selectedSlot,
  slots,
  canBook,
  onDateChange,
  onSlotSelect,
}: MentorBookingCardProps) => {
  return (
    <Card className="mentor-profile__booking-card">
      <div className="mentor-profile__booking-header">
        <p className="mentor-profile__booking-title">Book a session</p>
        <div className="mentor-profile__price-group">
          <p className="mentor-profile__price">
            {"\u20AC"} {price}
          </p>
          <p className="mentor-profile__price-unit">per hour</p>
        </div>
      </div>
      <p className="mentor-profile__select-date-label">Select date</p>
      <Calendar
        onChange={(date) => {
          if (date instanceof Date) {
            onDateChange(date);
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
                onClick={() => onSlotSelect(slot)}
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
        disabled={!canBook}
      >
        Confirm and book session
      </Button>
    </Card>
  );
};

export default MentorBookingCard;
