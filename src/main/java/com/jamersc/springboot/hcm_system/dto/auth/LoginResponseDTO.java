package com.jamersc.springboot.hcm_system.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String message;
    private String username;
    private Set<String> roles; // Send back the roles for client-side authorization
    // If using JWT, this would include the token: private String accessToken;
    private String accessToken;
    private String tokenType = "Bearer"; // A standard header for clarity
}
