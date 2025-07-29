package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.LoginRequestDTO;
import com.jamersc.springboot.hcm_system.dto.LoginResponseDTO;
import com.jamersc.springboot.hcm_system.service.AuthService;
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

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String registerAccount() {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest){
        // authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Set authentication in SecurityContext (important for session-based login and subsequent checks)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Build the response dto
        LoginResponseDTO response = new LoginResponseDTO(
                "Login successful",
                loginRequest.getUsername(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)// Get the role string (e.g., "ROLE_ADMIN")
                        .collect(Collectors.toSet())
        );

        // If you were using JWT, you'd generate and return the JWT token here
        // String jwt = jwtTokenProvider.generateToken(authentication);
        // response.setAccessToken(jwt);

        return ResponseEntity.ok(response); // Return 200 OK with success message and roles/token
    }

}
