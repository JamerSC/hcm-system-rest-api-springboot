package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_system.service.job.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Page<JobDTO>> getOpenJobs(
            @PageableDefault(page = 0, size = 10, sort = "title") Pageable pageable) {
        Page<JobDTO> listOfOpenJobs = jobService.getOpenJobs(pageable);
        return new ResponseEntity<>(listOfOpenJobs, HttpStatus.OK);
    }

    @PostMapping("/jobs/{id}/apply")
    public ResponseEntity<ApplicationResponseDTO> applyForJob(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO application = applicantService.applyForJob(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @GetMapping("/applications/jobs-applied")
    public ResponseEntity<Page<ApplicationResponseDTO>> getAllApplicantJobsApplied(
            @PageableDefault(page = 0, size = 10, sort = "status") Pageable pageable, Authentication authentication) {
        Page<ApplicationResponseDTO> appliedJobs = applicantService.getAllApplicantJobsApplied(pageable, authentication);
        return new ResponseEntity<>(appliedJobs, HttpStatus.OK);
    }

    @GetMapping("/application/{id}/view")
    public ResponseEntity<Optional<ApplicationResponseDTO>> getAppliedJob(
            @PathVariable Long id, Authentication authentication) {
        Optional<ApplicationResponseDTO> application = applicantService.getApplicantJobsAppliedById(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PatchMapping("/application/{id}/withdraw")
    public ResponseEntity<ApplicationResponseDTO> withdrawApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO withdrawn = applicantService.withdrawApplication(id, authentication);
        return new ResponseEntity<>(withdrawn, HttpStatus.OK);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<ApplicantProfileDTO> getMyApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ApplicantProfileDTO profile = applicantService.getApplicantProfile(username);
        return ResponseEntity.ok(profile);
    }

    // Endpoint for updating applicant profile details (e.g., after registration)
    // The @AuthenticationPrincipal allows you to get the currently logged-in user's details
    @PutMapping("/update-profile")
    public ResponseEntity<ApplicantProfileDTO> updateApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplicantProfileDTO profileDTO) {
        // userDetails.getUsername() gives you the username of the logged-in user
        ApplicantProfileDTO profile = applicantService.updateApplicantProfile(userDetails.getUsername(), profileDTO);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // Endpoint for CV/Resume upload
    @PostMapping("/profile/upload-resume")
    public ResponseEntity<String> uploadResume(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select the file to upload,", HttpStatus.BAD_REQUEST);
        }
        applicantService.uploadResume(userDetails.getUsername(), String.valueOf(file));
        return new ResponseEntity<>("Resume uploaded successfully", HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(Authentication authentication) {
        applicantService.deleteApplicantAccount(authentication);
        return new ResponseEntity<>(
                "User account and profile deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
