# Mentor Platform

Full-stack mentor marketplace application with a Spring Boot backend and a React/Vite frontend.

## Current Status

The backend contains the main domain model and REST API for authentication, users, mentors, skills, and bookings. JWT authentication is wired through Spring Security.

The frontend is a Vite React app with the initial authentication shell in place. It currently renders the login screen, stores JWTs in local storage, calls the backend auth endpoints through a shared Axios client, and loads the current user through `/api/users/me`.

## Tech Stack

- Backend: Java 21, Spring Boot 4, Spring Security, Spring Data JPA, Maven
- Database: PostgreSQL
- Auth: JWT bearer tokens
- Frontend: React 18, TypeScript, Vite, React Router, TanStack Query, Axios

## Project Structure

```txt
backend/
  src/main/java/com/frankaksenia/backend/
    config/        Security and helper configuration
    controller/    REST controllers
    dto/           API request/response DTOs
    exceptions/    Custom API exceptions
    filter/        JWT request filter
    mapper/        Entity-to-response mappers
    model/         JPA entities and enums
    repository/    Spring Data repositories
    service/       Business logic
  src/main/resources/application.properties

frontend/
  src/api/         Axios client and auth API wrappers
  src/auth/        AuthProvider and useAuth hook
  src/components/  Shared UI components
  src/router/      React Router setup
  src/types/       Frontend API/domain types
  src/utils/       Token storage helpers
  src/views/       Login view
```

## Backend Features

Implemented API areas:

- `POST /api/auth/register` creates a user and returns authentication data.
- `POST /api/auth/login` authenticates a username/password and returns a JWT token.
- `GET /api/users` returns users.
- `GET /api/users/me` returns the authenticated user.
- `PUT /api/users/me` updates the authenticated user.
- `POST /api/mentors/profile` creates a mentor profile for mentor users.
- `PUT /api/mentors/profile` updates the current mentor profile.
- `GET /api/skills` returns skills.
- `POST /api/skills` creates a skill and requires the `ADMIN` role.
- `GET /api/bookings` returns bookings.
- `GET /api/studentBookings?studentId=...` returns bookings for a student.
- `GET /api/mentorBookings?mentorId=...` returns bookings for a mentor.
- `POST /api/createBooking` creates a booking.

Review models, DTOs, repository, mapper, and service files exist, but the `ReviewController` is not implemented yet.

## Frontend Features

Implemented frontend pieces:

- Vite React TypeScript app shell.
- Router with `/login`, `/register`, and fallback redirect to `/login`.
- Login page with split layout, background image treatment, and form controls.
- Shared Axios client in `frontend/src/api/http.ts`.
- Auth API wrapper in `frontend/src/api/authApi.ts`.
- `AuthProvider` with login, register, logout, role checks, and current-user loading.
- Token persistence in `localStorage`.

Current frontend limitations:

- `/register` currently renders the login page as a placeholder.
- Most marketplace screens are not built yet.
- The backend login endpoint uses `username`, so the frontend login form is aligned to username/password.

## Local Setup

### Backend

Requirements:

- Java 21
- Maven wrapper from `backend/mvnw`
- PostgreSQL running locally

The backend is configured in `backend/src/main/resources/application.properties` for:

```txt
jdbc:postgresql://localhost:5432/mentorplatform
```

Run the backend:

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

Requirements:

- Node.js
- npm

Install dependencies:

```bash
cd frontend
npm install
```

Run the dev server:

```bash
npm run dev
```

Build the frontend:

```bash
npm run build
```

The Vite dev server proxies `/api` to `http://localhost:8080`. You can also set a frontend env var:

```txt
VITE_API_BASE_URL=/api
```

