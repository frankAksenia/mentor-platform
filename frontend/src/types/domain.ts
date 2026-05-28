export type UserRole = "STUDENT" | "MENTOR" | "ADMIN";

export type BookingStatus =
  | "PENDING"
  | "ACCEPTED"
  | "REJECTED"
  | "CANCELLED"
  | "COMPLETED";

export type AvailableSlot = {
  startTime: string;
  endTime: string;
};

export type MentorBookingCardProps = {
  price: number;
  selectedDate: Date;
  selectedSlot: AvailableSlot | null;
  slots: AvailableSlot[];
  canBook: boolean;
  onDateChange: (date: Date) => void;
  onSlotSelect: (slot: AvailableSlot) => void;
};

export interface User {
  id: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  role: UserRole;
  avatarUrl?: string | null;
  createdAt?: string;
  updatedAt?: string;
}

export interface Skill {
  id: string;
  name: string;
  category: string;
}

export interface MentorProfile {
  userId: string;
  mentorProfileId: string;
  image?: string | null;
  firstName: string;
  lastName: string;
  title: string;
  bio: string;
  hourlyRate: number;
  yearsOfExperience: number;
  languages: string[];
  skills: Skill[];
  averageRating: number;
  reviewsCount: number;
};


export interface Booking {
  id: string;
  student: User;
  mentor: MentorProfile;
  startTime: string;
  endTime: string;
  status: BookingStatus;
  topic: string;
  message?: string | null;
  meetingLink?: string | null;
  createdAt: string;
  updatedAt: string;
}

export interface Review {
  reviewId: string;
  comment: string;
  createdAt: string;
  rating: number;
  reviewerFirstName: string;
  reviewerLastName: string;
};

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}
