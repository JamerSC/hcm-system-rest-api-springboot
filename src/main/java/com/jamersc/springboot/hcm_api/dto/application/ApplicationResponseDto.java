package com.jamersc.springboot.hcm_api.dto.application;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.hcm_api.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDto {
    private Long applicationId;
    private String applicantName;
    private String appliedPosition;
    private String description;
    private ApplicationStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date appliedAt;
}
