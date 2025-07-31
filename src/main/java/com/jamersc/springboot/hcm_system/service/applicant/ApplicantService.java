package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;

import java.util.List;

public interface ApplicantService {

    List<ApplicantDto> getAllApplicant();
    ApplicantDto getApplicantById(Long id);
    void updateProfile(String username, ApplicantProfileDTO profileDTO);
    void saveResume(String username, String file);
}
