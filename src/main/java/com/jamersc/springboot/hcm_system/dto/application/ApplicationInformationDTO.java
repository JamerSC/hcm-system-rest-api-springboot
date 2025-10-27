package com.jamersc.springboot.hcm_system.dto.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.hcm_system.entity.ApplicationStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ApplicationInformationDTO {

    private Long id;

    // Applicant Info
    private String applicantName;
    private String email;
    private String phone;
    private String mobile;
    private String linkedInProfile;

    // Job Info
    private String jobPosition;
    private String jobDescription;

    // Application Info
    private final ApplicationStatus status = ApplicationStatus.NEW;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date appliedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date updatedAt;

    // Contract / Offer Info
    private Double expectedSalary;
    private Double proposedSalary;

    // Additional Information
    private String degree;
    private String source;
    private Date availability;
    private String applicationSummary;
    private String skills;
    private final Set<String> employees = new HashSet<>();

    // Audit Info
    private String updatedByUsername;
}
