import { http } from "./http";
import type { AuthResponse, LoginRequest, RegisterRequest } from "../types/api";
import type { User } from "../types/domain";

export async function registerUser(
  payload: RegisterRequest,
): Promise<AuthResponse> {
  const { data } = await http.post<AuthResponse>("/auth/register", payload);
  return data;
}

export async function loginUser(payload: LoginRequest): Promise<AuthResponse> {
  const { data } = await http.post<AuthResponse | string>(
    "/auth/login",
    payload,
  );
  return typeof data === "string" ? { token: data } : data;
}

export async function fetchMe(): Promise<User> {
  const { data } = await http.get<User>("/users/me");
  return data;
}
