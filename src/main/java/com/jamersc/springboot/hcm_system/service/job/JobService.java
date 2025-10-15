package com.jamersc.springboot.hcm_system.service.job;

import com.jamersc.springboot.hcm_system.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Page<JobDTO> getAllJob(Pageable pageable);
    Page<JobResponseDTO> getOpenJobs(Pageable pageable);
    Optional<JobResponseDTO> getJobById(Long id);
    JobResponseDTO save(JobCreateDTO dto, Authentication authentication);
    JobResponseDTO postJob(Long id, Authentication authentication);
    JobResponseDTO filledJob(Long id, Authentication authentication);
    JobResponseDTO closeJob(Long id, Authentication authentication);
    void deleteById(Long id);
}
