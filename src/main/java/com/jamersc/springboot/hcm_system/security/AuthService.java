package com.jamersc.springboot.hcm_system.security;

import com.jamersc.springboot.hcm_system.dto.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.User;

public interface AuthService {
    User registerNewUser(RegistrationRequestDTO request);
}
