package com.frankaksenia.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.MentorAvailabilityResponse;
import com.frankaksenia.backend.dto.MentorProfileCreateRequest;
import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.dto.MentorProfileUpdateRequest;
import com.frankaksenia.backend.dto.MentorReviewsResponse;
import com.frankaksenia.backend.service.MentorAvailabilityService;
import com.frankaksenia.backend.service.MentorProfileService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final MentorProfileService mentorService;

    private final MentorAvailabilityService mentorAvailabilityService;

    public MentorController(MentorProfileService mentorService, MentorAvailabilityService mentorAvailabilityService) {
        this.mentorService = mentorService;
        this.mentorAvailabilityService = mentorAvailabilityService;
    }


    @GetMapping
    public ResponseEntity<List<MentorProfileResponse>> getAllMentors() {
        List<MentorProfileResponse> mentors = mentorService.getAllMentors();
        return ResponseEntity.status(HttpStatus.OK).body(mentors);
    }


    @PostMapping("/profile")
    public ResponseEntity<MentorProfileResponse> createMentorProfile(@Valid @RequestBody MentorProfileCreateRequest request) {
        MentorProfileResponse mentorProfileResponse = mentorService.createMentorProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorProfileResponse);
    }

    @PutMapping("/profile")
    public ResponseEntity<MentorProfileResponse> updateMentorProfile(@Valid @RequestBody MentorProfileUpdateRequest request) {
        MentorProfileResponse mentorProfileResponse = mentorService.updateMentorProfile(request);
        return ResponseEntity.status(HttpStatus.OK).body(mentorProfileResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<MentorProfileResponse> getMentorProfile(@RequestParam UUID mentorId) {
        MentorProfileResponse mentorProfileResponse = mentorService.getMentorProfile(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(mentorProfileResponse);
    }

    @GetMapping("/{mentorId}/availability")
    public ResponseEntity<List<MentorAvailabilityResponse>> getMentorAvailability(@PathVariable UUID mentorId, @RequestParam LocalDate date) {
        List<MentorAvailabilityResponse> availability = mentorAvailabilityService.getMentorAvailability(mentorId, date);
        return ResponseEntity.status(HttpStatus.OK).body(availability);
    }

    @GetMapping("/{mentorId}/reviews")
    public ResponseEntity<List<MentorReviewsResponse>> getMentorReviews(@PathVariable UUID mentorId) {
        List<MentorReviewsResponse> reviews = mentorService.getMentorReviews(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
    
    

}
