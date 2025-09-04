package com.jamersc.springboot.hcm_system.service.auth;

import com.jamersc.springboot.hcm_system.dto.auth.ChangePasswordDTO;
import com.jamersc.springboot.hcm_system.dto.auth.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    User registerNewUserAndApplicant(RegistrationRequestDTO request);
    void changePassword(ChangePasswordDTO changePasswordDTO, Authentication authentication);
}
