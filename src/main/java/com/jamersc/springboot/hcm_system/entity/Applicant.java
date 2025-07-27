package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // One user account maps to one applicant profile
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // Link to the User account

    private String cvFilePath; // Path or URL to stored CV/Resume
    private String currentStatus; // e.g., "Registered", "CV Submitted", "Interviewed"

    // ... other fields for their application profile (education, experience, personal details)
    private String firstName;
    private String lastName;
    // etc.
}
