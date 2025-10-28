package com.jamersc.springboot.hcm_api.service.application;

import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface ApplicationService {
    Page<ApplicationResponseDTO> getAllApplication(Pageable pageable);
    Optional<ApplicationResponseDTO> getApplicationById(Long id);
    ApplicationResponseDTO updateApplicationInformation(Long id, ApplicationUpdateDTO dto, Authentication authentication);
    ApplicationResponseDTO initialQualification(Long id, Authentication authentication);
    ApplicationResponseDTO firstInterview(Long id, Authentication authentication);
    ApplicationResponseDTO secondInterview(Long id, Authentication authentication);
    ApplicationResponseDTO contractProposal(Long id, Authentication authentication);
    ApplicationResponseDTO contractSigned(Long id, Authentication authentication);
    ApplicationResponseDTO approveApplication(Long id, Authentication authentication);
    ApplicationResponseDTO rejectApplication(Long id, Authentication authentication);
    ApplicationResponseDTO hireApplication(Long id, Authentication authentication);
}
