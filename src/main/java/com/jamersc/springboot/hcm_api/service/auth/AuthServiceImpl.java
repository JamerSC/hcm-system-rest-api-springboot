package com.jamersc.springboot.hcm_api.service.auth;

import com.jamersc.springboot.hcm_api.dto.auth.ChangePasswordDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginResponseDto;
import com.jamersc.springboot.hcm_api.dto.auth.RegistrationDto;
import com.jamersc.springboot.hcm_api.entity.Applicant;
import com.jamersc.springboot.hcm_api.entity.Role;
import com.jamersc.springboot.hcm_api.entity.User;
import com.jamersc.springboot.hcm_api.mapper.AuthMapper;
import com.jamersc.springboot.hcm_api.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_api.repository.RoleRepository;
import com.jamersc.springboot.hcm_api.repository.UserRepository;
import com.jamersc.springboot.hcm_api.security.JwtTokenProvider;
import com.jamersc.springboot.hcm_api.service.email.EmailService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder passwordEncoder;
    private final ApplicantRepository applicantRepository;
    private final AuthMapper authMapper;
    private final EmailService emailService;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ApplicantRepository applicantRepository, AuthMapper authMapper, EmailService emailService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicantRepository = applicantRepository;
        this.authMapper = authMapper;
        this.emailService = emailService;
    }

    @Override
    public User registerNewApplicantUser(RegistrationDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered!");
        }

        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setEmail(dto.getEmail());
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword())); // <-- HASH THE PASSWORD HERE

        // Assign default role, e.g., "ROLE_APPLICANT"
        Role applicantRole = roleRepository.findByRoleName("ROLE_APPLICANT") // Make sure this matches your DB role name
                .orElseThrow(() -> new RuntimeException("ROLE_APPLICANT not found in database!"));
        Set<Role> roles = new HashSet<>();
        roles.add(applicantRole);
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);

        // Create initial applicant profile linked to the new user
        Applicant newApplicant = new Applicant();
        newApplicant.setFirstName(dto.getFirstName());
        newApplicant.setLastName(dto.getLastName());
        newApplicant.setUser(savedUser);
        // Save applicant profile
        applicantRepository.save(newApplicant);

        // Send email to applicant using Email Service
        emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getFullName());

        // Save new user
        return savedUser;
    }

    @Override
    public LoginResponseDto login(LoginDto dto) {
        try {
            // authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );

            // retrieve user details
            User user = userRepository.findByUsername(dto.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + dto.getUsername()));
            // map
            LoginResponseDto responseDto = authMapper.entityToLoginResponseDto(user);

            // Generate JWT token
            String jwt = tokenProvider.generateToken(authentication);
            responseDto.setAccessToken(jwt);

            return responseDto;

        } catch (org.springframework.security.core.AuthenticationException ex) {
            // Convert Spring's AuthenticationException to your custom one
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    public void changePassword(ChangePasswordDto dto, Authentication authentication) {
        // get authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()->new RuntimeException("Authenticated user not found."));

        // verify old password
        if (!passwordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }

        // hash and set the new password
        String newHashedPassword = passwordEncoder.encode(
                dto.getNewPassword()
        );
        currentUser.setPassword(newHashedPassword);
        currentUser.setUpdatedBy(currentUser);

        // save updated user entity
        userRepository.save(currentUser);

    }
}
