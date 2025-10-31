package com.jamersc.springboot.hcm_api.service.auth;

import com.jamersc.springboot.hcm_api.dto.auth.ChangePasswordDto;
import com.jamersc.springboot.hcm_api.dto.auth.RegistrationDto;
import com.jamersc.springboot.hcm_api.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    User registerNewUserAndApplicant(RegistrationDto request);
    void changePassword(ChangePasswordDto changePasswordDTO, Authentication authentication);
}
