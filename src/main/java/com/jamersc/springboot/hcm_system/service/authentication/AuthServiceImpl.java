package com.jamersc.springboot.hcm_system.service.authentication;

import com.jamersc.springboot.hcm_system.dto.registration.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Role;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_system.repository.RoleRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import com.jamersc.springboot.hcm_system.service.authentication.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;
    private final ApplicantRepository applicantRepository;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ApplicantRepository applicantRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicantRepository = applicantRepository;
    }

    @Override
    @Transactional
    public User registerNewUserAndApplicant(RegistrationRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
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
        Role applicantRole = roleRepository.findByRoleName("ROLE_APPLICANT") // Make sure this matches your DB role name
                .orElseThrow(() -> new RuntimeException("ROLE_APPLICANT not found in database!"));
        Set<Role> roles = new HashSet<>();
        roles.add(applicantRole);
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);

        // Create initial applicant profile linked to the new user
        Applicant newApplicant = new Applicant();
        newApplicant.setFirstName(request.getFirstName());
        newApplicant.setLastName(request.getLastName());
        newApplicant.setUser(savedUser);
        applicantRepository.save(newApplicant);

        return savedUser;
    }
}
