package com.jamersc.springboot.hcm_api.service.applicant;

import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantProfileDto;
import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantResponseDto;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ApplicantService {

    Page<ApplicantResponseDto> getAllApplicant(Pageable pageable); // for admin/hr role
    Optional<ApplicantResponseDto> getApplicantById(Long id); // for admin/hr role
    ApplicantResponseDto getApplicantProfile(String username);
    ApplicantResponseDto updateApplicantProfile(String username, ApplicantProfileDto profileDTO);
    ApplicantResponseDto uploadResume(MultipartFile file, Authentication authentication);
    ApplicationResponseDto applyForJob(Long id, Authentication authentication);
    Page<ApplicationResponseDto> getAllApplicantJobsApplied(Pageable pageable, Authentication authentication);
    Optional<ApplicationResponseDto> getApplicantJobsAppliedById(Long id, Authentication authentication);
    ApplicationResponseDto withdrawApplication(Long id, Authentication authentication);
    void deleteApplicantAccount(Authentication authentication);
}
