package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantResponseDto;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDto;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationUpdateDto;
import com.jamersc.springboot.hcm_api.service.applicant.ApplicantService;
import com.jamersc.springboot.hcm_api.service.application.ApplicationService;
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
    public ResponseEntity<Page<ApplicantResponseDto>> getAllApplicants(
            @PageableDefault(page = 0, size = 10, sort = "lastName") Pageable pageable) {
        Page<ApplicantResponseDto> listOfApplicants = applicantService.getAllApplicant(pageable);
        return new ResponseEntity<>(listOfApplicants, HttpStatus.OK);
    }

    @GetMapping("/{id}/applicant")
    public ResponseEntity<Optional<ApplicantResponseDto>> getApplicantById(
            @PathVariable Long id) {
        Optional<ApplicantResponseDto> profile = applicantService.getApplicantById(id);
        return ResponseEntity.ok(profile);
    }

    /*** APPLICATION **/
    @GetMapping("/applications")
    public ResponseEntity<Page<ApplicationResponseDto>> getAllApplicationsSubmitted(
            @PageableDefault(page = 0, size = 10, sort = "status") Pageable pageable) {
        Page<ApplicationResponseDto> applications = applicationService.getAllApplication(pageable);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{id}/application")
    public ResponseEntity<Optional<ApplicationResponseDto>> getApplicationById(
            @PathVariable Long id) {
        Optional<ApplicationResponseDto> application = applicationService.getApplicationById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PutMapping("/{id}/update-application")
    public ResponseEntity<ApplicationResponseDto> updateApplication(
            @PathVariable Long id, @RequestBody ApplicationUpdateDto dto, Authentication authentication) {
        ApplicationResponseDto updatedApplication = applicationService.updateApplicationInformation(id, dto, authentication);
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/initial-qualification")
    public ResponseEntity<ApplicationResponseDto> initialQualification(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto initialQualification = applicationService.initialQualification(id, authentication);
        return new ResponseEntity<>(initialQualification, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/first-interview")
    public ResponseEntity<ApplicationResponseDto> firstInterview(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto firstInterview = applicationService.firstInterview(id, authentication);
        return new ResponseEntity<>(firstInterview, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/second-interview")
    public ResponseEntity<ApplicationResponseDto> secondInterview(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto secondInterview = applicationService.secondInterview(id, authentication);
        return new ResponseEntity<>(secondInterview, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/contract-proposal")
    public ResponseEntity<ApplicationResponseDto> contractProposal(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto contractProposal = applicationService.contractProposal(id, authentication);
        return new ResponseEntity<>(contractProposal, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/contract-signed")
    public ResponseEntity<ApplicationResponseDto> contractSigned(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto contractSigned = applicationService.contractSigned(id, authentication);
        return new ResponseEntity<>(contractSigned, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/approve")
    public ResponseEntity<ApplicationResponseDto> approveApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto application = applicationService.approveApplication(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/reject")
    public ResponseEntity<ApplicationResponseDto> rejectApplication(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto application = applicationService.rejectApplication(id, authentication);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PatchMapping("/{id}/application/hire")
    public ResponseEntity<ApplicationResponseDto> hireApplicant(
            @PathVariable Long id, Authentication authentication) {
        ApplicationResponseDto hireApplication = applicationService.hireApplication(id, authentication);
        return new ResponseEntity<>(hireApplication, HttpStatus.OK);
    }
}
