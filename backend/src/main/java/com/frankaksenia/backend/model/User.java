package com.frankaksenia.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Booking> studentBookings = new ArrayList<>();
    @OneToMany(mappedBy = "mentor")
    @JsonIgnore
    private List<Booking> mentorBookings = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Review> writtenReviews = new ArrayList<>();
     @OneToMany(mappedBy = "mentor")
    @JsonIgnore
    private List<Review> receivedReviews = new ArrayList<>();
}
