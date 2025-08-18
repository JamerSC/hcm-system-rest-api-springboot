package com.jamersc.springboot.hcm_system.dto.application;


import com.jamersc.springboot.hcm_system.entity.ApplicantStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {

    private Long id;

    private String applicantName;

    private String jobPosition;

    private ApplicantStatus status = ApplicantStatus.SUBMITTED;

    private Date appliedAt;

    private String updatedByUsername;

    private Date updatedAt;
}
