package com.jamersc.springboot.hcm_system.dto.job;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.hcm_system.entity.Department;
import com.jamersc.springboot.hcm_system.entity.JobStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {

    private String title;
    private String description;
    private String requirements;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private String location;
    private LocalDate postedDate;
    private String department;
    private String createdByUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date createdAt;
    private String updatedByUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date updatedAt;
}
