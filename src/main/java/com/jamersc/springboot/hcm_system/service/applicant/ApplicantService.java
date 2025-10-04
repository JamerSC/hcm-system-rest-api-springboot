package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    Page<ApplicantDto> getAllApplicant(Pageable pageable); // for admin/hr role
    Optional<ApplicantDto> getApplicantById(Long id); // for admin/hr role
    ApplicantProfileDTO getApplicantProfile(String username);
    void updateApplicantProfile(String username, ApplicantProfileDTO profileDTO);
    void saveResume(String username, String file);
    void applyForJob(Long id, Authentication authentication);
    List<ApplicationResponseDTO> getAllApplicantJobsApplied(Authentication authentication);
    Optional<ApplicationResponseDTO> getApplicantJobsAppliedById(Long id, Authentication authentication);
    void cancelApplication(Long id, Authentication authentication);
    void deleteApplicantAccount(Authentication authentication);
}
