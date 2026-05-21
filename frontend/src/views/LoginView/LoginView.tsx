import { FormEvent, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../auth/AuthContext";
import { Button } from "../../components/Button/Button";
import { Field } from "../../components/Field/Field";
import { toApiError } from "../../api/http";
import "./LoginView.css";

export const LoginPage = () => {
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setError(null);
    try {
      await login({ username, password });
      const from =
        (location.state as { from?: Location })?.from?.pathname ?? "/mentors";
      navigate(from, { replace: true });
    } catch (err) {
      setError(toApiError(err));
    }
  };

  return (
    <main className="login-view">
      <div className="login-view__panel login-view__panel--brand">
        <div className="login-view__content">
          <p className="login-view__headline login-view__headline--light">
            Welcome to MentorScale
          </p>
          <p className="login-view__intro">
            The professional network for meaningful growth. Connect with
            industry experts, book 1:1 sessions, and accelerate your career.
          </p>
        </div>
      </div>

      <div className="login-view__panel login-view__panel--form">
        <div className="login-view__content">
          <div>
            <h2 className="login-view__headline">Login to Account</h2>
            <p className="login-view__subtitle">
              Please enter your details to continue.
            </p>
          </div>

          <form onSubmit={handleSubmit} className="login-view__form">
            <Field
              label="Username"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />

            <Field
              label="Password"
              labelExtra={
                <a href="#" className="login-view__link">
                  Forgot Password?
                </a>
              }
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            {error && <p className="login-view__error">{error}</p>}

            <Button type="submit" className="login-view__submit-button">
              Login to MentorScale{" "}
              <span className="login-view__submit-icon">→</span>
            </Button>
          </form>

          <p className="login-view__register-text">
            Don't have an account?{" "}
            <Link className="login-view__link login-view__register-link" to="/register">
              Sign up for free
            </Link>
          </p>
        </div>
      </div>
    </main>
  );
};
