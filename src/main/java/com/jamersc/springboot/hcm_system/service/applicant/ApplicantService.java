package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    List<ApplicantDto> getAllApplicant(); // for admin/hr role
    Optional<ApplicantDto> getApplicantById(Long id); // for admin/hr role
    ApplicantProfileDTO getApplicantProfile(String username);
    void updateApplicantProfile(String username, ApplicantProfileDTO profileDTO);
    void saveResume(String username, String file);
    Application applyForJob(String username, Long jobId);
}
