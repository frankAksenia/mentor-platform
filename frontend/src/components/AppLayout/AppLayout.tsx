import { Link, NavLink, Outlet } from "react-router-dom";
import "./AppLayout.css";

export const AppLayout = () => {
  return (
    <div className="app-shell">
      <header className="app-header">
        <div className="app-header-inner">
          <Link className="app-brand" to="/">
            MentorScale
          </Link>

          <nav className="app-nav" aria-label="Primary navigation">
            <NavLink to="/mentors">Find Mentors</NavLink>
            <a href="#">How it works</a>
            <a href="#">Resources</a>
          </nav>
        </div>
      </header>

      <div className="app-main">
        <Outlet />
      </div>

      <footer className="app-footer">
        <div className="app-footer-inner">
          <span>&copy; 2026 MentorScale. All rights reserved.</span>

          <nav className="app-footer-nav" aria-label="Footer navigation">
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
            <a href="#">Cookie Policy</a>
            <a href="#">Contact Support</a>
          </nav>
        </div>
      </footer>
    </div>
  );
};
