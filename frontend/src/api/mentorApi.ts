import { http } from "./http";
import type { MentorProfile } from "../types/domain";

export const fetchMentors = async (): Promise<MentorProfile[]> => {
  const { data } = await http.get<MentorProfile[]>("/mentors");
  return data;
};

export const fetchMentor = async (id: string): Promise<MentorProfile> => {
  const { data } = await http.get<MentorProfile>(`/mentors/${id}`);
  return data;
};
