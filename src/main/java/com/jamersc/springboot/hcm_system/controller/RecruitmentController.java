package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.service.applicant.ApplicantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentController {
    final private ApplicantService applicantService;

    public RecruitmentController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/{id}/profile")
    private ResponseEntity<Optional<ApplicantDto>> getApplicantProfileById(
            @PathVariable Long id) {
        Optional<ApplicantDto> profile = applicantService.getApplicantById(id);
        return ResponseEntity.ok(profile);
    }

}
