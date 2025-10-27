package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDTO;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantResponseDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationUpdateDTO;
import com.jamersc.springboot.hcm_system.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_system.service.application.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ApplicantResponseDTO>> getAllApplicants(
            @PageableDefault(page = 0, size = 10, sort = "lastName") Pageable pageable) {
        Page<ApplicantResponseDTO> listOfApplicants = applicantService.getAllApplicant(pageable);
        return new ResponseEntity<>(listOfApplicants, HttpStatus.OK);
    }

    @GetMapping("/{id}/applicant")
    public ResponseEntity<Optional<ApplicantResponseDTO>> getApplicantById(
            @PathVariable Long id) {
        Optional<ApplicantResponseDTO> profile = applicantService.getApplicantById(id);
        return ResponseEntity.ok(profile);
    }

    /*** APPLICATION **/
    @GetMapping("/applications")
    public ResponseEntity<Page<ApplicationResponseDTO>> getAllApplicationsSubmitted(
            @PageableDefault(page = 0, size = 10, sort = "status") Pageable pageable) {
        Page<ApplicationResponseDTO> applications = applicationService.getAllApplication(pageable);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{id}/application")
    public ResponseEntity<Optional<ApplicationResponseDTO>> getApplicationById(
            @PathVariable Long id) {
        Optional<ApplicationResponseDTO> application = applicationService.getApplicationById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PutMapping("/{id}/update-application")
    public ResponseEntity<ApplicationResponseDTO> updateApplication(
            @PathVariable Long id, @RequestBody ApplicationUpdateDTO dto, Authentication authentication) {
        ApplicationResponseDTO updatedApplication = applicationService.updateApplicationInformation(id, dto, authentication);
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/initial-qualification")
    public ResponseEntity<ApplicationResponseDTO> initialQualification(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO initialQualification = applicationService.initialQualification(id, authentication);
        return new ResponseEntity<>(initialQualification, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/first-interview")
    public ResponseEntity<ApplicationResponseDTO> firstInterview(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO firstInterview = applicationService.firstInterview(id, authentication);
        return new ResponseEntity<>(firstInterview, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/second-interview")
    public ResponseEntity<ApplicationResponseDTO> secondInterview(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO secondInterview = applicationService.secondInterview(id, authentication);
        return new ResponseEntity<>(secondInterview, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/contract-proposal")
    public ResponseEntity<ApplicationResponseDTO> contractProposal(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO contractProposal = applicationService.contractProposal(id, authentication);
        return new ResponseEntity<>(contractProposal, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/contract-signed")
    public ResponseEntity<ApplicationResponseDTO> contractSigned(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO contractSigned = applicationService.contractSigned(id, authentication);
        return new ResponseEntity<>(contractSigned, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/approve")
    public ResponseEntity<ApplicationResponseDTO> approveApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDTO application = applicationService.approveApplication(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.OK);
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
