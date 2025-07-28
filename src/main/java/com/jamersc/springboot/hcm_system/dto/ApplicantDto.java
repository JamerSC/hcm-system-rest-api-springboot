package com.jamersc.springboot.hcm_system.dto;

import com.jamersc.springboot.hcm_system.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDto {
    //private Long id;

    //@OneToOne // One user account maps to one applicant profile
    //@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    //private User user; // Link to the User account

    private String cvFilePath; // Path or URL to stored CV/Resume
    private String currentStatus; // e.g., "Registered", "CV Submitted", "Interviewed"
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String educationLevel;

}
