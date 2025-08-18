package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_system.service.job.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final JobService jobService;

    public ApplicantController(ApplicantService applicantService, JobService jobService) {
        this.applicantService = applicantService;
        this.jobService = jobService;
    }

    @GetMapping("/open/jobs")
    public ResponseEntity<List<JobDTO>> getOpenJobs() {
        List<JobDTO> listOfOpenJobs = jobService.getOpenJobs();
        return new ResponseEntity<>(listOfOpenJobs, HttpStatus.OK);
    }

    @PostMapping("/jobs/{id}/apply")
    public ResponseEntity<String> applyForJob(@PathVariable Long id,
                                                   Authentication authentication) {
        applicantService.applyForJob(id, authentication);
        return new ResponseEntity<>("Successfully applied for the job.", HttpStatus.CREATED);
    }

    @GetMapping("/me/profile")
    private ResponseEntity<ApplicantProfileDTO> getMyApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ApplicantProfileDTO profile = applicantService.getApplicantProfile(username);
        return ResponseEntity.ok(profile);
    }

    // Endpoint for updating applicant profile details (e.g., after registration)
    // The @AuthenticationPrincipal allows you to get the currently logged-in user's details
    @PutMapping("/profile")
    public ResponseEntity<String> updateApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplicantProfileDTO profileDTO) {
        // userDetails.getUsername() gives you the username of the logged-in user
        applicantService.updateApplicantProfile(userDetails.getUsername(), profileDTO);
        return ResponseEntity.ok("Applicant profile updated successfully!");
    }

    // Endpoint for CV/Resume upload
    @PostMapping("/profile/resume")
    public ResponseEntity<String> uploadResume(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select the file to upload,", HttpStatus.BAD_REQUEST);
        }
        applicantService.saveResume(userDetails.getUsername(), String.valueOf(file));
        return new ResponseEntity<>("Resume uploaded successfully", HttpStatus.OK);
    }
}
