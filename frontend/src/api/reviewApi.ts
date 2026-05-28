import { http } from "./http";
import type { Review } from "../types/domain";

export const fetchReviewsForMentor = async (
  mentorId: string,
): Promise<Review[]> => {
  const { data } = await http.get<Review[]>(`/mentors/${mentorId}/reviews`);

  console.log("Fetched reviews data:", data); // IGNORE

  return data;
};
