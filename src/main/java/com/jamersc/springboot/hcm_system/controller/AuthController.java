package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.login.LoginRequestDTO;
import com.jamersc.springboot.hcm_system.dto.login.LoginResponseDTO;
import com.jamersc.springboot.hcm_system.dto.registration.RegistrationRequestDTO;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.security.JwtTokenProvider;
import com.jamersc.springboot.hcm_system.service.authentication.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
        User user = authService.registerNewUserAndApplicant(requestDTO);
        return ResponseEntity.ok("Registered User: " + user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest){
        // authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        // Unlike session-based, we don't need to set the context here for later requests.
        // We only need it for the token generation below.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // *** THIS IS THE KEY CHANGE ***
        // Generate JWT token
        String jwt = tokenProvider.generateToken(authentication);

        // Build the response dto
        LoginResponseDTO response = new LoginResponseDTO(
                "Login successful",
                loginRequest.getUsername(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)// Get the role string (e.g., "ROLE_ADMIN")
                        .collect(Collectors.toSet()),
                jwt,
                "Bearer"
        );

        // If you were using JWT, you'd generate and return the JWT token here
        // String jwt = jwtTokenProvider.generateToken(authentication);
        // response.setAccessToken(jwt);

        return ResponseEntity.ok(response); // Return 200 OK with success message and roles/token
    }

}
