package com.jamersc.springboot.hcm_system.service.authentication;

import com.jamersc.springboot.hcm_system.dto.registration.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.User;

public interface AuthService {
    User registerNewUserAndApplicant(RegistrationRequestDTO request);
}
