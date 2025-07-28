package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.ApplicantProfileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicantService {

    List<ApplicantDto> getAllApplicant();
    ApplicantDto getApplicantById(Long id);
    void updateProfile(String username, ApplicantProfileDTO profileDTO);
    void saveResume(String username, String file);
}
