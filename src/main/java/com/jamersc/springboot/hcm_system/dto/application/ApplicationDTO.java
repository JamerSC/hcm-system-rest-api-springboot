package com.jamersc.springboot.hcm_system.dto.application;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.hcm_system.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private String applicantName;
    private String jobPosition;
    private String jobDescription;
    private ApplicationStatus status = ApplicationStatus.NEW;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date appliedAt;
    private String updatedByUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date updatedAt;
}
