package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false, referencedColumnName = "id")
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicantStatus status = ApplicantStatus.SUBMITTED;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id", referencedColumnName = "id")
    private User updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
