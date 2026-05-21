import { http } from "./http";
import type { AuthResponse, LoginRequest, RegisterRequest } from "../types/api";
import type { User } from "../types/domain";

export const registerUser = async (
  payload: RegisterRequest,
): Promise<AuthResponse> => {
  const { data } = await http.post<AuthResponse>("/auth/register", payload);
  return data;
};

export const loginUser = async (
  payload: LoginRequest,
): Promise<AuthResponse> => {
  const { data } = await http.post<AuthResponse | string>(
    "/auth/login",
    payload,
  );
  return typeof data === "string" ? { token: data } : data;
};

export const fetchMe = async (): Promise<User> => {
  const { data } = await http.get<User>("/users/me");
  return data;
};
