import type {
  ReactNode,
  InputHTMLAttributes,
  SelectHTMLAttributes,
  TextareaHTMLAttributes,
} from "react";

import "./Field.css";

interface BaseProps {
  label: string;
  labelExtra?: ReactNode;
  error?: string;
}

export function Field({
  label,
  labelExtra,
  error,
  ...props
}: BaseProps & InputHTMLAttributes<HTMLInputElement>) {
  return (
    <label className="field">
      <span className="field-label-row">
        <span className="field-label-text">{label}</span>
        {labelExtra && <span className="field-label-extra">{labelExtra}</span>}
      </span>
      <input {...props} />

      {error && <small className="error">{error}</small>}
    </label>
  );
}

export function TextAreaField({
  label,
  error,
  ...props
}: BaseProps & TextareaHTMLAttributes<HTMLTextAreaElement>) {
  return (
    <label className="field">
      <span>{label}</span>
      <textarea {...props} />
      {error && <small className="error">{error}</small>}
    </label>
  );
}

export function SelectField({
  label,
  error,
  children,
  ...props
}: BaseProps & SelectHTMLAttributes<HTMLSelectElement>) {
  return (
    <label className="field">
      <span>{label}</span>
      <select {...props}>{children}</select>
      {error && <small className="error">{error}</small>}
    </label>
  );
}
