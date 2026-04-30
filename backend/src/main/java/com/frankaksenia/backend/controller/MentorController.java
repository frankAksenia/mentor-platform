package com.frankaksenia.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.MentorProfileCreateRequest;
import com.frankaksenia.backend.dto.MentorProfileResponse;
import com.frankaksenia.backend.dto.MentorProfileUpdateRequest;
import com.frankaksenia.backend.service.MentorService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/mentors/profile")
    public ResponseEntity<MentorProfileResponse> createMentorProfile(@Valid @RequestBody MentorProfileCreateRequest request) {
        MentorProfileResponse mentorProfileResponse = mentorService.createMentorProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorProfileResponse);
    }

    @PutMapping("/mentors/profile")
    public ResponseEntity<MentorProfileResponse> updateMentorProfile(@Valid @RequestBody MentorProfileUpdateRequest request) {
        MentorProfileResponse mentorProfileResponse = mentorService.updateMentorProfile(request);
        return ResponseEntity.status(HttpStatus.OK).body(mentorProfileResponse);
    }
    

}
