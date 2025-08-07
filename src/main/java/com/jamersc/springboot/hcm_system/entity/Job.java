package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String requirements;

    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.OPEN; // Default

    private String location;

    private LocalDate postedDate;

    // A job belongs to a department, and a department can have many jobs.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    // An employee (e.g., a manager or admin) posts a job.
    // This establishes a Many-to-One relationship.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by_employee_id", referencedColumnName = "id")
    private Employee postedBy;
}
