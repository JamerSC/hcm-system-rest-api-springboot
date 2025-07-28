package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.Role;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.repository.RoleRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import com.jamersc.springboot.hcm_system.security.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService  {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerNewUser(RegistrationRequestDTO request) {
        if (userRepository.findUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered!");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // <-- HASH THE PASSWORD HERE

        // Assign default role, e.g., "ROLE_APPLICANT"
        Role applicantRole = roleRepository.findByName("ROLE_APPLICANT") // Make sure this matches your DB role name
                .orElseThrow(() -> new RuntimeException("ROLE_APPLICANT not found in database!"));
        newUser.setRoles(Collections.singleton(applicantRole)); // Assign the single role to the Set

        return userRepository.save(newUser);
    }
}
