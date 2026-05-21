import { ChevronDown, Search } from "lucide-react";
import { Link } from "react-router-dom";
import { Button } from "../../components/Button/Button";
import { Card } from "../../components/Card/Card";
import { IoIosStar } from "react-icons/io";
import type { MentorPreview } from "../../types/domain";
import "./FindMentorView.css";

const placeholderMentors: MentorPreview[] = [
  {
    title: "Product Strategy",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuAWnWViImVlhZOqEG0XvqJN6NnV2jo6pZcxRPX-K4iCKMh_HjtN4p3oPJlKg1LVu-BAiWstu_7w8JuuMdbd_xg2mOjkn7AIZgdNyr83QFws9MSf-aACmdFEFwv2taHXo2WJHinsRU-K6SY65UjaYm8dsdbtkLlEkFw5dd2kM4h-Shnq2RSMWbNOBbQuXgoDxr--VVF1XhdPdbVLIPlZuFjVBHDa1t22viWhK0XfPiejQW-OOeMCJki5g-PjIWiJzqL7Akulyi5gD4o",
    first_name: "Ksusha",
    last_name: "Frank",
    price: 90,
    rating: 4.5,
    experience: 10,
    bio: "Experienced product leader with a passion for helping teams build impactful products. I have over 10 years of experience in product management, working with startups and large enterprises to define and execute product strategies that drive growth and customer satisfaction.",
    num_reviews: 120,
    skills: ["Roadmaps", "Discovery", "Stakeholder Alignment"],
    languages: ["English", "Spanish"],
  },
  {
    title: "Frontend Engineering",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuAWnWViImVlhZOqEG0XvqJN6NnV2jo6pZcxRPX-K4iCKMh_HjtN4p3oPJlKg1LVu-BAiWstu_7w8JuuMdbd_xg2mOjkn7AIZgdNyr83QFws9MSf-aACmdFEFwv2taHXo2WJHinsRU-K6SY65UjaYm8dsdbtkLlEkFw5dd2kM4h-Shnq2RSMWbNOBbQuXgoDxr--VVF1XhdPdbVLIPlZuFjVBHDa1t22viWhK0XfPiejQW-OOeMCJki5g-PjIWiJzqL7Akulyi5gD4o",
    first_name: "John",
    last_name: "Doe",
    price: 80,
    rating: 4.2,
    experience: 8,
    bio: "Senior frontend engineer with a focus on building scalable and maintainable web applications. I have a strong background in React and TypeScript, and I enjoy mentoring junior developers to help them grow their skills and advance their careers.",
    num_reviews: 85,
    skills: ["React", "TypeScript", "Design Systems"],
    languages: ["English"],
  },
  {
    title: "Career Growth",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuAWnWViImVlhZOqEG0XvqJN6NnV2jo6pZcxRPX-K4iCKMh_HjtN4p3oPJlKg1LVu-BAiWstu_7w8JuuMdbd_xg2mOjkn7AIZgdNyr83QFws9MSf-aACmdFEFwv2taHXo2WJHinsRU-K6SY65UjaYm8dsdbtkLlEkFw5dd2kM4h-Shnq2RSMWbNOBbQuXgoDxr--VVF1XhdPdbVLIPlZuFjVBHDa1t22viWhK0XfPiejQW-OOeMCJki5g-PjIWiJzqL7Akulyi5gD4o",
    first_name: "Jane",
    last_name: "Smith",
    price: 70,
    rating: 4.7,
    experience: 15,
    bio: "Career coach with a focus on helping tech professionals navigate their career paths and achieve their goals. I have experience in both individual coaching and group workshops, and I am passionate about empowering others to reach their full potential.",
    num_reviews: 200,
    skills: ["Interview Prep", "Promotion Planning", "Leadership"],
    languages: ["English", "French"],
  },
  {
    title: "Professional eater",
    first_name: "Persik",
    last_name: "Frank",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuAWnWViImVlhZOqEG0XvqJN6NnV2jo6pZcxRPX-K4iCKMh_HjtN4p3oPJlKg1LVu-BAiWstu_7w8JuuMdbd_xg2mOjkn7AIZgdNyr83QFws9MSf-aACmdFEFwv2taHXo2WJHinsRU-K6SY65UjaYm8dsdbtkLlEkFw5dd2kM4h-Shnq2RSMWbNOBbQuXgoDxr--VVF1XhdPdbVLIPlZuFjVBHDa1t22viWhK0XfPiejQW-OOeMCJki5g-PjIWiJzqL7Akulyi5gD4o",
    price: 0,
    rating: 5.0,
    experience: 5,
    bio: "I am a professional eater with a passion for food and a love of sleep. I have been eating professionally for over 5 years, and I have a deep understanding of the culinary world and the art of eating. I am always looking for new and exciting foods to try, and I am dedicated to sharing my love of food with others.",
    num_reviews: 999,
    skills: ["Eating", "Sleeping"],
    languages: ["English"],
  },
];

export const FindMentorPage = () => {
  return (
    <main className="find-mentor-page">
      <header className="find-mentor-header">
        <div>
          <h1>Discover your perfect mentor</h1>
        </div>
      </header>

      <form className="find-mentor-toolbar" aria-label="Mentor search">
        <label className="find-mentor-search" htmlFor="mentor-search">
          <Search size={18} aria-hidden="true" />
          <input
            id="mentor-search"
            type="search"
            placeholder="Search by role, company, or skill..."
            aria-label="Search mentors"
          />
        </label>

        <div className="line-divider" aria-hidden="true" />

        <button className="find-mentor-select" type="button">
          Expertise <ChevronDown size={16} aria-hidden="true" />
        </button>
        <button className="find-mentor-select" type="button">
          Availability <ChevronDown size={16} aria-hidden="true" />
        </button>
        <button className="find-mentor-select" type="button">
          Price Range <ChevronDown size={16} aria-hidden="true" />
        </button>
        <Button className="find-mentor-submit" type="submit">
          Search
        </Button>
      </form>

      <section className="mentor-card-grid" aria-label="Mentor results">
        {placeholderMentors.map((mentor, index) => (
          <Card
            as="article"
            className="mentor-card"
            key={`${mentor.first_name}-${mentor.last_name}-${index}`}
          >
            <div className="mentor-card-top-container">
              <img
                className="mentor-profile-image"
                src={mentor.image}
                alt={mentor.title}
              />
              <div className="mentor-card-price-rating">
                <p>
                  <IoIosStar />
                  {mentor.rating} ({mentor.num_reviews}){" "}
                </p>{" "}
                <p>
                  {" "}
                  From {"\u20AC"}
                  {mentor.price}/hr
                </p>
              </div>
            </div>
            <div className="mentor-card-profile-info-container">
              <h2>
                {" "}
                {mentor.first_name} {mentor.last_name}
              </h2>
              <h3>{mentor.title}</h3>
              <p>{mentor.bio}</p>
            </div>
            <Link
              className="view-profile-button"
              to={`/mentors/${index + 1}`}
              state={{ mentor }}
            >
              View profile
            </Link>
          </Card>
        ))}
      </section>
    </main>
  );
};
