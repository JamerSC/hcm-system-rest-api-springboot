package com.jamersc.springboot.hcm_system.dto.job;

import com.jamersc.springboot.hcm_system.entity.JobStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCreateDTO {
    private String title;
    private String description;
    private String requirements;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private String location;
    private LocalDate postedDate;
    private String department;
}
