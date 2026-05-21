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
    <main className="login-page">
      <div className="split left">
        <div className="centered">
          <p className="headline-large white">Welcome to MentorScale </p>
          <p className="paragraph-text">
            The professional network for meaningful growth. Connect with
            industry experts, book 1:1 sessions, and accelerate your career.
          </p>
        </div>
      </div>

      <div className="split right">
        <div className="centered">
          <div>
            <h2 className="headline-large">Login to Account</h2>
            <p className="lower-text">Please enter your details to continue.</p>
          </div>

          <form onSubmit={handleSubmit} className="login-form">
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
                <a href="#" className="link">
                  Forgot Password?
                </a>
              }
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            {error && <p className="error">{error}</p>}

            <Button type="submit" className="login-button">
              Login to MentorScale <span className="login-button-arrow">→</span>
            </Button>
          </form>

          <p className="muted">
            Don't have an account?{" "}
            <Link className="link space" to="/register">
              Sign up for free
            </Link>
          </p>
        </div>
      </div>
    </main>
  );
};
