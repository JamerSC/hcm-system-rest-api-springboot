package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // applicants user id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false , referencedColumnName = "id")
    private Applicant applicant; // name of applicant

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false, referencedColumnName = "id")
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.NEW;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id", referencedColumnName = "id")
    private User updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    // other subject information
    private String email;
    private String phone;
    private String mobile;
    private String linkedInProfile; // link or name text field
    private String degree; // degree table college, associate, masters single selection - cms
    private Set<String> employees = new HashSet<>(); // arrays of employees
    private String source; // create source fb, linkedin, single selection - cms
    private Date availability; // date picker
    // contract
    private Double expectedSalary;
    private Double proposedSalary;
    // tabs
    private String applicationSummary;
    private String skills; // it should be a cms of skills



    @PrePersist
    protected void onCreate() {
        appliedAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}
