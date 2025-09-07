package com.jamersc.springboot.hcm_system.service.application;

import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<ApplicationResponseDTO> getAllApplication();
    Optional<ApplicationResponseDTO> getApplicationById(Long id);
    ApplicationResponseDTO initialQualification(Long id, Authentication authentication);
    ApplicationResponseDTO firstInterview(Long id, Authentication authentication);
    ApplicationResponseDTO secondInterview(Long id, Authentication authentication);
    ApplicationResponseDTO contractProposal(Long id, Authentication authentication);
    ApplicationResponseDTO contractSigned(Long id, Authentication authentication);
    ApplicationResponseDTO approveApplication(Long id, Authentication authentication);
    ApplicationResponseDTO rejectApplication(Long id, Authentication authentication);
    ApplicationResponseDTO hireApplication(Long id, Authentication authentication);
}
