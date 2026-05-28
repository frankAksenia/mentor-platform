import { ChevronDown, Search } from "lucide-react";
import { Button } from "../../components/Button/Button";
import MentorCard from "./MentorCard";
import "./FindMentorView.css";
import { useQuery } from "@tanstack/react-query";
import { fetchMentors } from "../../api/mentorApi";

const FindMentorPage = () => {
  const { data: mentors = [] } = useQuery({
    queryKey: ["mentors"],
    queryFn: fetchMentors,
  });

  return (
    <main className="find-mentor-view">
      <header className="find-mentor-view__header">
        <div>
          <h1 className="find-mentor-view__title">
            Discover your perfect mentor
          </h1>
        </div>
      </header>

      <form className="find-mentor-view__toolbar" aria-label="Mentor search">
        <label className="find-mentor-view__search" htmlFor="mentor-search">
          <Search size={18} aria-hidden="true" />
          <input
            id="mentor-search"
            type="search"
            placeholder="Search by role, company, or skill..."
            aria-label="Search mentors"
          />
        </label>

        <div className="line-divider" aria-hidden="true" />

        <button className="find-mentor-view__filter-button" type="button">
          Expertise <ChevronDown size={16} aria-hidden="true" />
        </button>
        <button className="find-mentor-view__filter-button" type="button">
          Availability <ChevronDown size={16} aria-hidden="true" />
        </button>
        <button className="find-mentor-view__filter-button" type="button">
          Price Range <ChevronDown size={16} aria-hidden="true" />
        </button>
        <Button className="find-mentor-view__submit-button" type="submit">
          Search
        </Button>
      </form>

      <section
        className="find-mentor-view__results"
        aria-label="Mentor results"
      >
        {mentors.map((mentor, index) => (
          <MentorCard
            key={`${mentor.firstName}-${mentor.lastName}-${index}`}
            mentor={mentor}
            profilePath={`/mentors/${mentor.mentorProfileId}`}
          />
        ))}
      </section>
    </main>
  );
};

export default FindMentorPage;
