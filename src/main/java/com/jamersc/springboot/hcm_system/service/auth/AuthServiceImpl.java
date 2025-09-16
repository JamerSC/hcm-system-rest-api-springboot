package com.jamersc.springboot.hcm_system.service.auth;

import com.jamersc.springboot.hcm_system.dto.auth.ChangePasswordDTO;
import com.jamersc.springboot.hcm_system.dto.auth.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Role;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_system.repository.RoleRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import com.jamersc.springboot.hcm_system.service.email.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;
    private final ApplicantRepository applicantRepository;
    private final EmailService emailService;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ApplicantRepository applicantRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicantRepository = applicantRepository;
        this.emailService = emailService;
    }

    @Override
    public User registerNewUserAndApplicant(RegistrationRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered!");
        }

        User newUser = new User();
        newUser.setFirstName(requestDTO.getFirstName());
        newUser.setLastName(requestDTO.getLastName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setUsername(requestDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword())); // <-- HASH THE PASSWORD HERE

        // Assign default role, e.g., "ROLE_APPLICANT"
        Role applicantRole = roleRepository.findByRoleName("ROLE_APPLICANT") // Make sure this matches your DB role name
                .orElseThrow(() -> new RuntimeException("ROLE_APPLICANT not found in database!"));
        Set<Role> roles = new HashSet<>();
        roles.add(applicantRole);
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);

        // Create initial applicant profile linked to the new user
        Applicant newApplicant = new Applicant();
        newApplicant.setFirstName(requestDTO.getFirstName());
        newApplicant.setLastName(requestDTO.getLastName());
        newApplicant.setUser(savedUser);
        // Save applicant profile
        applicantRepository.save(newApplicant);

        // Send email to applicant using Email Service
        emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getUserFullName());

        // Save new user
        return savedUser;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO, Authentication authentication) {
        // get authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()->new RuntimeException("Authenticated user not found."));

        // verify old password
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }

        // hash and set the new password
        String newHashedPassword = passwordEncoder.encode(
                changePasswordDTO.getNewPassword()
        );
        currentUser.setPassword(newHashedPassword);
        currentUser.setUpdatedBy(currentUser);

        // save updated user entity
        userRepository.save(currentUser);

    }
}
