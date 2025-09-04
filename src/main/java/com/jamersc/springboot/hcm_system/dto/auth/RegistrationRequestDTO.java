package com.jamersc.springboot.hcm_system.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {
    @NotBlank(message = "Username is required!")
    @Size(min = 8, max = 20, message = "Username must be between 8 & 20 characters!")
    private String username;
    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password must be at least 8 characters long!")
    private String password;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email format!")
    private String email;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
}
