import { createBrowserRouter, Navigate } from "react-router-dom";
import { AppLayout } from "../components/AppLayout/AppLayout";
import { FindMentorPage } from "../views/FindMentorView/FindMentorView";
import { LoginPage } from "../views/LoginView/LoginView";
import { MentorProfileView } from "../views/MentorProfileView/MentorProfileView";

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
    path: "/",
    element: <AppLayout />,
    children: [
      {
        index: true,
        element: <FindMentorPage />,
      },
      {
        path: "mentors",
        element: <FindMentorPage />,
      },
      {
        path: "mentors/:mentorId",
        element: <MentorProfileView />,
      },
      {
        path: "*",
        element: <Navigate to="/login" replace />,
      },
    ],
  },
]);
