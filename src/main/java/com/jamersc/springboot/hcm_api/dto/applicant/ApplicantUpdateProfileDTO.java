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
public class ApplicantUpdateProfileDTO {
    // Remove 'id' as it's typically derived from the authenticated user
    // Remove 'user' entity

    // These fields are typically updated via a separate file upload or internal logic
    // So you might remove them if client shouldn't set them directly in profile update
    // private String cvFilePath;
    // private String currentStatus;

    // ... other fields for their application profile (education, experience, personal details)
    @NotBlank(message = "First name is required for profile") // example validation
    private String firstName;
    @NotBlank(message = "Last name is required for profile")
    private String lastName;
    private String phoneNumber;
    private String address;
    private String educationLevel;
    // view user email & username
    private String email;
    private String username;
}
