package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private String cvFilePath; // Path or URL to stored CV/Resume
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String educationLevel;

    @OneToOne // One user account maps to one applicant profile
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // Link to the User account

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id", referencedColumnName = "id")
    private User updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    // Add JPA annotations for createdDate and modifiedDate
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public String getApplicantFullName() {
        return this.firstName + " " + this.lastName;
    }
}
