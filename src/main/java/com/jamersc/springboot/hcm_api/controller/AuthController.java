package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.auth.ChangePasswordDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginResponseDto;
import com.jamersc.springboot.hcm_api.dto.auth.RegistrationDto;
import com.jamersc.springboot.hcm_api.entity.User;
import com.jamersc.springboot.hcm_api.security.JwtTokenProvider;
import com.jamersc.springboot.hcm_api.service.auth.AuthService;
import com.jamersc.springboot.hcm_api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerNewApplicantUser(@Valid @RequestBody RegistrationDto requestDto) {
        User registeredApplicantUser = authService.registerNewApplicantUser(requestDto);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Applicant user registered successfully!")
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> authenticateUser(
            @Valid @RequestBody LoginDto dto){
        LoginResponseDto loginResponse = authService.login(dto);
        ApiResponse<LoginResponseDto> response = ApiResponse.<LoginResponseDto>builder()
                .success(true)
                .message("Login successfully")
                .data(loginResponse)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('LOGOUT_USERS')")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logoutUser() {
        // Clear the security context for the current request.
        // This is important in a stateless environment to ensure no
        // subsequent code within the same request lifecycle is treated
        // as authenticated.
        SecurityContextHolder.clearContext();
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("User logged out successfully!")
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('CHANGE_PASSWORD_USERS')")
    @PatchMapping("/me/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @Valid @RequestBody ChangePasswordDto changePasswordDTO,
            Authentication authentication) {
        authService.changePassword(changePasswordDTO, authentication);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("User password changed successfully!!")
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
