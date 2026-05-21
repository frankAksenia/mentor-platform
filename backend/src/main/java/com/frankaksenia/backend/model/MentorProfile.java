package com.frankaksenia.backend.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="mentor_profile")
public class MentorProfile {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne
    private User user;

    private String title;

    private String bio;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    private Set<String> languages;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "average_rating")
    private float averageRating;

    @Column(name = "reviews_count")
    private int reviewsCount;
    
    @ManyToMany
    private Set<Skill> skills = new HashSet<>();
}
