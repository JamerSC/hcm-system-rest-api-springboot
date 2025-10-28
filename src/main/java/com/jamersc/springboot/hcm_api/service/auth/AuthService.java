package com.jamersc.springboot.hcm_api.service.auth;

import com.jamersc.springboot.hcm_api.dto.auth.ChangePasswordDTO;
import com.jamersc.springboot.hcm_api.dto.auth.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_api.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    User registerNewUserAndApplicant(RegistrationRequestDTO request);
    void changePassword(ChangePasswordDTO changePasswordDTO, Authentication authentication);
}
