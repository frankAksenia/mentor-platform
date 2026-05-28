import { http } from "./http";
import type { MentorProfile } from "../types/domain";

export const fetchMentors = async (): Promise<MentorProfile[]> => {
  const { data } = await http.get<MentorProfile[]>("mentors");

  console.log("Fetched mentors data:", data); // IGNORE
  return data;
};
