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
    private Long jobId;
    private LocalDate postedDate;
    private String postedBy;
    private String title;
    private String department;
    private String description;
    private String location;
    private JobStatus status;
    private String requirements;
}
