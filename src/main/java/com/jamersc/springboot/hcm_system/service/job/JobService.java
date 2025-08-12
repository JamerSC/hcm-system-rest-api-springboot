package com.jamersc.springboot.hcm_system.service.job;

import com.jamersc.springboot.hcm_system.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Job;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<JobDTO> getAllJob();
    Optional<JobDTO> getJobById(Long id);
    JobResponseDTO save(JobCreateDTO dto, Authentication authentication);
    void deleteById(Long id);
}
