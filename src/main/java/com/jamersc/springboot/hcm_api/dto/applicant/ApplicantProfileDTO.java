package com.jamersc.springboot.hcm_api.dto.applicant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantProfileDTO {
    @NotBlank(message = "First name is required for profile") // example validation
    private String firstName;
    @NotBlank(message = "Last name is required for profile")
    private String lastName;
    private String phoneNumber;
    private String address;
    private String educationLevel;
    private String cvFilePath;
    // view user email & username
    private String email;
    private String username;
}
