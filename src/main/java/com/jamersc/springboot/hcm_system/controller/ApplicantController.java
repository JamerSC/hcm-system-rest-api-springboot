package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.service.ApplicantService;
import com.jamersc.springboot.hcm_system.service.ApplicantServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    final private ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    // Endpoint for updating applicant profile details (e.g., after registration)
    // The @AuthenticationPrincipal allows you to get the currently logged-in user's details
    @PutMapping("/profile")
    public ResponseEntity<String> updateApplicantProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplicantProfileDTO profileDTO) {
        // userDetails.getUsername() gives you the username of the logged-in user
        applicantService.updateProfile(userDetails.getUsername(), profileDTO);
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
