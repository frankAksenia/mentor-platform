import { createBrowserRouter, Navigate } from "react-router-dom";
import { LoginPage } from "../views/LoginView/LoginView";

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/register",
    element: <LoginPage />,
  },
  {
    path: "*",
    element: <Navigate to="/login" replace />,
  },
]);
