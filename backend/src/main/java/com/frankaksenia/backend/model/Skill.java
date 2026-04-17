package com.frankaksenia.backend.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skill {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    @ManyToMany(mappedBy = "skills")
    private Set<MentorProfile> mentorProfiles = new HashSet<>();
}
