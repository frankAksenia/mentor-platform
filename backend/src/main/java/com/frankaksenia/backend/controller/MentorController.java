package com.frankaksenia.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.MentorAvailabilityResponse;
import com.frankaksenia.backend.dto.MentorProfileCreateRequest;
import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.dto.MentorProfileUpdateRequest;
import com.frankaksenia.backend.dto.MentorReviewsResponse;
import com.frankaksenia.backend.service.MentorAvailabilityService;
import com.frankaksenia.backend.service.MentorService;

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
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final MentorService mentorService;

    private final MentorAvailabilityService mentorAvailabilityService;

    public MentorController(MentorService mentorService, MentorAvailabilityService mentorAvailabilityService) {
        this.mentorService = mentorService;
        this.mentorAvailabilityService = mentorAvailabilityService;
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

    @GetMapping("/availability")
    public ResponseEntity<List<MentorAvailabilityResponse>> getMentorAvailability(@RequestParam UUID mentorId, @RequestParam LocalDate date) {
        List<MentorAvailabilityResponse> availability = mentorAvailabilityService.getMentorAvailability(mentorId, date);
        return ResponseEntity.status(HttpStatus.OK).body(availability);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<MentorReviewsResponse>> getMentorReviews(@RequestParam UUID mentorId) {
        List<MentorReviewsResponse> reviews = mentorService.getMentorReviews(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
    
    

}
