import { http } from "./http";
import type { MentorProfile, AvailableSlot } from "../types/domain";

export const fetchMentors = async (): Promise<MentorProfile[]> => {
  const { data } = await http.get<MentorProfile[]>("mentors");

  console.log("Fetched mentors data:", data); // IGNORE
  return data;
};

export const fetchMentorAvailability = async (mentorId: string, date: string): Promise<AvailableSlot[]> => {
  const { data } = await http.get<AvailableSlot[]>(`mentors/${mentorId}/availability`, {
    params: { date },
  });

  console.log("Fetched mentor availability data:", data); // IGNORE
  return data.map((slot) => ({
    startTime: `${date}T${slot.startTime}`,
    endTime: `${date}T${slot.endTime}`,
  }));
}
