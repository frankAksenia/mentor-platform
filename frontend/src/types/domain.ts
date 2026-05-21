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
  id: string;
  user: User;
  title: string;
  bio: string;
  hourlyRate: number;
  yearsOfExperience: number;
  languages: string[];
  active: boolean;
  skills: Skill[];
  averageRating: number;
  reviewCount: number;
}

export interface MentorPreview {
  title: string;
  image: string;
  first_name: string;
  last_name: string;
  price: number;
  rating: number;
  experience: number;
  bio: string;
  num_reviews: number;
  skills: string[];
  languages: string[];
}

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
  id: string;
  booking: Booking;
  student: User;
  mentor: MentorProfile;
  rating: 1 | 2 | 3 | 4 | 5;
  comment: string;
  createdAt: string;
}

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}
