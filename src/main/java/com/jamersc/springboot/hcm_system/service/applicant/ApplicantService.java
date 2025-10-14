package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface ApplicantService {

    Page<ApplicantDto> getAllApplicant(Pageable pageable); // for admin/hr role
    Optional<ApplicantDto> getApplicantById(Long id); // for admin/hr role
    ApplicantProfileDTO getApplicantProfile(String username);
    ApplicantProfileDTO updateApplicantProfile(String username, ApplicantProfileDTO profileDTO);
    void uploadResume(String username, String file);
    ApplicationResponseDTO applyForJob(Long id, Authentication authentication);
    Page<ApplicationResponseDTO> getAllApplicantJobsApplied(Pageable pageable, Authentication authentication);
    Optional<ApplicationResponseDTO> getApplicantJobsAppliedById(Long id, Authentication authentication);
    ApplicationResponseDTO withdrawApplication(Long id, Authentication authentication);
    void deleteApplicantAccount(Authentication authentication);
}
