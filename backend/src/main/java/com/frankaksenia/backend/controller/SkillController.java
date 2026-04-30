package com.frankaksenia.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.SkillRequest;
import com.frankaksenia.backend.dto.SkillResponse;
import com.frankaksenia.backend.service.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillResponse>> getMethodName() {
        List<SkillResponse> skillResponses = skillService.getSkills();
        return ResponseEntity.status(HttpStatus.OK).body(skillResponses);
    }

    @PostMapping("/skills")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponse> createSkill(@RequestBody SkillRequest skillResponse) {
        SkillResponse createdSkill = skillService.createSkill(skillResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSkill);
    }

}
