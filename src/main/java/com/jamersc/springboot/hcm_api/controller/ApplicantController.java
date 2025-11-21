package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantProfileDto;
import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantResponseDto;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDto;
import com.jamersc.springboot.hcm_api.dto.job.JobResponseDto;
import com.jamersc.springboot.hcm_api.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_api.service.job.JobService;
import com.jamersc.springboot.hcm_api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final JobService jobService;

    public ApplicantController(ApplicantService applicantService, JobService jobService) {
        this.applicantService = applicantService;
        this.jobService = jobService;
    }

    @GetMapping("/open/jobs")
    public ResponseEntity<ApiResponse<Page<JobResponseDto>>> getOpenJobs(
            @PageableDefault(page = 0, size = 10, sort = "title") Pageable pageable) {
        Page<JobResponseDto> listOfOpenJobs = jobService.getOpenJobs(pageable);
        ApiResponse<Page<JobResponseDto>> response = ApiResponse.<Page<JobResponseDto>>builder()
                .success(true)
                .message("List of open jobs retrieved successfully!")
                .data(listOfOpenJobs)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/jobs/{id}/apply")
    public ResponseEntity<ApiResponse<ApplicationResponseDto>> applyForJob(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto applicationSubmitted = applicantService.applyForJob(id, authentication);
        ApiResponse<ApplicationResponseDto> response = ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Job application submitted successfully!")
                .data(applicationSubmitted)
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/applications/jobs-applied")
    public ResponseEntity<ApiResponse<Page<ApplicationResponseDto>>> getAllApplicantJobsApplied(
            @PageableDefault(page = 0, size = 10, sort = "status") Pageable pageable, Authentication authentication) {
        Page<ApplicationResponseDto> appliedJobs = applicantService.getAllApplicantJobsApplied(pageable, authentication);
        ApiResponse<Page<ApplicationResponseDto>> response = ApiResponse.<Page<ApplicationResponseDto>>builder()
                .success(true)
                .message("Applicant jobs applied retrieved successfully!")
                .data(appliedJobs)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/application/{id}/view")
    public ResponseEntity<ApiResponse<Optional<ApplicationResponseDto>>> getAppliedJob(
            @PathVariable Long id, Authentication authentication) {
        Optional<ApplicationResponseDto> appliedJob = applicantService.getApplicantJobsAppliedById(id, authentication);
        ApiResponse<Optional<ApplicationResponseDto>> response = ApiResponse.<Optional<ApplicationResponseDto>>builder()
                .success(true)
                .message("Job application submitted successfully!")
                .data(appliedJob)
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/application/{id}/withdraw")
    public ResponseEntity<ApplicationResponseDto> withdrawApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto withdrawn = applicantService.withdrawApplication(id, authentication);
        return new ResponseEntity<>(withdrawn, HttpStatus.OK);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<ApplicantResponseDto> getMyApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ApplicantResponseDto profile = applicantService.getApplicantProfile(username);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // Endpoint for updating applicant profile details (e.g., after registration)
    // The @AuthenticationPrincipal allows you to get the currently logged-in user's details
    @PutMapping("/update-profile")
    public ResponseEntity<ApplicantResponseDto> updateApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplicantProfileDto profileDTO) {
        // userDetails.getUsername() gives you the username of the logged-in user
        ApplicantResponseDto profile = applicantService.updateApplicantProfile(userDetails.getUsername(), profileDTO);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // Endpoint for CV/Resume upload
    @PostMapping("/profile/upload-resume")
    public ResponseEntity<ApplicantResponseDto> uploadResume(
            @Valid @RequestParam("file") MultipartFile file,
            Authentication authentication) {

        ApplicantResponseDto uploadedResume = applicantService.uploadResume(file, authentication);

        return new ResponseEntity<>(uploadedResume, HttpStatus.CREATED);
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(Authentication authentication) {
        applicantService.deleteApplicantAccount(authentication);
        return new ResponseEntity<>(
                "User account and profile deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
