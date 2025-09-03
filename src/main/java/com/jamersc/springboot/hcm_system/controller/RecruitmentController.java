package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_system.service.application.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recruitments")
public class RecruitmentController {
    private final ApplicantService applicantService;
    private final ApplicationService applicationService;

    public RecruitmentController(ApplicantService applicantService, ApplicationService applicationService) {
        this.applicantService = applicantService;
        this.applicationService = applicationService;
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantDto>> getAllApplicants() {
        List<ApplicantDto> listOfApplicants = applicantService.getAllApplicant();

        if (listOfApplicants.isEmpty()) {
            return ResponseEntity.noContent().build(); // No Content - HTTP 204
        }
        return new ResponseEntity<>(listOfApplicants, HttpStatus.OK);
    }

    @GetMapping("/{id}/applicant")
    public ResponseEntity<Optional<ApplicantDto>> getApplicantById(
            @PathVariable Long id) {
        Optional<ApplicantDto> profile = applicantService.getApplicantById(id);
        return ResponseEntity.ok(profile);
    }

    /*** APPLICATION **/
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplicationsSubmitted() {
        List<ApplicationResponseDTO> applications = applicationService.getAllApplication();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{id}/application")
    public ResponseEntity<Optional<ApplicationResponseDTO>> getApplicationById(
            @PathVariable Long id) {
        Optional<ApplicationResponseDTO> application = applicationService.getApplicationById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/review")
    public ResponseEntity<ApplicationResponseDTO> reviewApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO reviewApplication = applicationService.reviewApplication(id, authentication);
        return new ResponseEntity<>(reviewApplication, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/schedule")
    public ResponseEntity<ApplicationResponseDTO> scheduleApplicant(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO scheduleInterview = applicationService.scheduleInterview(id, authentication);
        return new ResponseEntity<>(scheduleInterview, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/reject")
    public ResponseEntity<ApplicationResponseDTO> rejectApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO application = applicationService.rejectApplication(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/hire")
    public ResponseEntity<ApplicationResponseDTO> hireApplicant(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO hireApplication = applicationService.hireApplication(id, authentication);
        return new ResponseEntity<>(hireApplication, HttpStatus.OK);
    }
}
