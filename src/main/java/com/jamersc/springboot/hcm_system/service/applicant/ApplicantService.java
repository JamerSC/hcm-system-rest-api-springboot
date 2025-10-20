package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDTO;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantResponseDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ApplicantService {

    Page<ApplicantDTO> getAllApplicant(Pageable pageable); // for admin/hr role
    Optional<ApplicantDTO> getApplicantById(Long id); // for admin/hr role
    ApplicantResponseDTO getApplicantProfile(String username);
    ApplicantResponseDTO updateApplicantProfile(String username, ApplicantProfileDTO profileDTO);
    ApplicantResponseDTO uploadResume(MultipartFile file, Authentication authentication);
    ApplicationResponseDTO applyForJob(Long id, Authentication authentication);
    Page<ApplicationResponseDTO> getAllApplicantJobsApplied(Pageable pageable, Authentication authentication);
    Optional<ApplicationResponseDTO> getApplicantJobsAppliedById(Long id, Authentication authentication);
    ApplicationResponseDTO withdrawApplication(Long id, Authentication authentication);
    void deleteApplicantAccount(Authentication authentication);
}
