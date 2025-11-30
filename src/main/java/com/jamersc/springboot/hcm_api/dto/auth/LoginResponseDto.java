package com.jamersc.springboot.hcm_api.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String fullName;
    private String username;
    private boolean active;
    private Set<String> roles;
    private Set<String> permissions;
    private String accessToken;
    private String tokenType = "Bearer"; // A standard header for clarity
}
