import type { UserRole } from "./domain";

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  role: Extract<UserRole, "STUDENT" | "MENTOR">;
}

export interface AuthResponse {
  token: string;
}

export interface UpdateMeRequest {
  firstName: string;
  lastName: string;
  avatarUrl?: string;
}

export interface MentorSearchParams {
  search?: string;
  skill?: string;
  minPrice?: number;
  maxPrice?: number;
  language?: string;
  minExperience?: number;
  sortBy?: "rating" | "price" | "experience";
  direction?: "asc" | "desc";
  page?: number;
  size?: number;
}

export interface UpsertMentorProfileRequest {
  title: string;
  bio: string;
  hourlyRate: number;
  yearsOfExperience: number;
  languages: string[];
  active: boolean;
  skillIds: string[];
}

export interface CreateBookingRequest {
  mentorId: string;
  startTime: string;
  endTime: string;
  topic: string;
  message?: string;
}

export interface CreateReviewRequest {
  bookingId: string;
  rating: number;
  comment: string;
}
