import { createContext, useContext, useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { fetchMe, loginUser, registerUser } from "../api/authApi";
import { http } from "../api/http";
import { clearToken, getToken, setToken } from "../utils/storage";
import type { LoginRequest, RegisterRequest } from "../types/api";
import type { User, UserRole } from "../types/domain";

interface AuthContextValue {
  user: User | undefined;
  isAuthenticated: boolean;
  isLoading: boolean;
  login: (payload: LoginRequest) => Promise<void>;
  register: (payload: RegisterRequest) => Promise<void>;
  logout: () => void;
  hasRole: (roles: UserRole | UserRole[]) => boolean;
}

const AuthContext = createContext<AuthContextValue | null>(null);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const queryClient = useQueryClient();
  const [token, setAuthToken] = useState<string | null>(() => getToken());

  const meQuery = useQuery({
    queryKey: ["me"],
    queryFn: fetchMe,
    enabled: Boolean(token),
    retry: false,
  });

  const loginMutation = useMutation({
    mutationFn: loginUser,
    onSuccess: async ({ token }) => {
      setToken(token);
      setAuthToken(token);
      http.defaults.headers.common.Authorization = `Bearer ${token}`;
      await queryClient.fetchQuery({ queryKey: ["me"], queryFn: fetchMe });
    },
  });

  const registerMutation = useMutation({
    mutationFn: registerUser,
    onSuccess: async ({ token }) => {
      setToken(token);
      setAuthToken(token);
      http.defaults.headers.common.Authorization = `Bearer ${token}`;
      await queryClient.fetchQuery({ queryKey: ["me"], queryFn: fetchMe });
    },
  });

  const value = useMemo<AuthContextValue>(
    () => ({
      user: meQuery.data,
      isAuthenticated: Boolean(meQuery.data),
      isLoading:
        meQuery.isLoading ||
        loginMutation.isPending ||
        registerMutation.isPending,
      login: async (payload) => {
        await loginMutation.mutateAsync(payload);
      },
      register: async (payload) => {
        await registerMutation.mutateAsync(payload);
      },
      logout: () => {
        clearToken();
        delete http.defaults.headers.common.Authorization;
        setAuthToken(null);
        queryClient.clear();
        window.location.assign("/login");
      },
      hasRole: (roles) => {
        const allowed = Array.isArray(roles) ? roles : [roles];
        return Boolean(meQuery.data && allowed.includes(meQuery.data.role));
      },
    }),
    [
      meQuery.data,
      meQuery.isLoading,
      loginMutation.isPending,
      registerMutation.isPending,
      queryClient,
    ],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth must be used inside AuthProvider");
  return ctx;
};
