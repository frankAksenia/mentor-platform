import type { ElementType, HTMLAttributes, ReactNode } from "react";

type CardProps<T extends ElementType = "section"> = {
  as?: T;
  children: ReactNode;
  className?: string;
} & HTMLAttributes<HTMLElement>;

export const Card = <T extends ElementType = "section">({
  as,
  children,
  className = "",
  ...props
}: CardProps<T>) => {
  const Component = as ?? "section";
  const classes = ["card", className].filter(Boolean).join(" ");

  return (
    <Component className={classes} {...props}>
      {children}
    </Component>
  );
};
