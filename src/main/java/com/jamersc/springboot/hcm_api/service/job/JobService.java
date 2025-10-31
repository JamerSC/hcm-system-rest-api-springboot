package com.jamersc.springboot.hcm_api.service.job;

import com.jamersc.springboot.hcm_api.dto.job.JobCreateDto;
import com.jamersc.springboot.hcm_api.dto.job.JobDto;
import com.jamersc.springboot.hcm_api.dto.job.JobPatchDto;
import com.jamersc.springboot.hcm_api.dto.job.JobResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface JobService {

    Page<JobDto> getAllJob(Pageable pageable);
    Page<JobResponseDto> getOpenJobs(Pageable pageable);
    Optional<JobResponseDto> getJobById(Long id);
    JobResponseDto save(JobCreateDto dto, Authentication authentication);
    JobResponseDto postJob(Long id, Authentication authentication);
    JobResponseDto patchJob(Long id, JobPatchDto dto, Authentication authentication);
    JobResponseDto filledJob(Long id, Authentication authentication);
    JobResponseDto closeJob(Long id, Authentication authentication);
    void deleteById(Long id);
}
