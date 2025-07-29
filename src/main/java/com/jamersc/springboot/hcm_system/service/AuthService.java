package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.User;

public interface AuthService {
    User registerNewUserAndApplicant(RegistrationRequestDTO request);
}
