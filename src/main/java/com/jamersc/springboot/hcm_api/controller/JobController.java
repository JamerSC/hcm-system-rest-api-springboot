package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.job.JobCreateDto;
import com.jamersc.springboot.hcm_api.dto.job.JobDto;
import com.jamersc.springboot.hcm_api.dto.job.JobPatchDto;
import com.jamersc.springboot.hcm_api.dto.job.JobResponseDto;
import com.jamersc.springboot.hcm_api.service.job.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<JobDto>> getAllJobs(
            @PageableDefault(page = 0, size = 10, sort = "title") Pageable pageable) {
        Page<JobDto> jobs = jobService.getAllJob(pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<JobResponseDto>> getJobById(@PathVariable Long id) {
        Optional<JobResponseDto> jobResponse = jobService.getJobById(id);
        return ResponseEntity.ok(jobResponse);
    }

    @PostMapping("/")
    public ResponseEntity<JobResponseDto> createJob(
            @Valid @RequestBody JobCreateDto createDTO,
            Authentication authentication) {
        JobResponseDto jobResponseDTO = jobService.save(createDTO, authentication);
        return new ResponseEntity<>(jobResponseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobResponseDto> patchJob(
            @PathVariable Long id, @RequestBody JobPatchDto dto, Authentication authentication) {
        JobResponseDto patchedJob = jobService.patchJob(id, dto, authentication);
        return new ResponseEntity<>(patchedJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/open")
    public ResponseEntity<JobResponseDto> postJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDto postJob = jobService.postJob(id, authentication);
        return new ResponseEntity<>(postJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponseDto> closeJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDto closeJob = jobService.closeJob(id, authentication);
        return new ResponseEntity<>(closeJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/filled")
    public ResponseEntity<JobResponseDto> filledJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDto filledJob = jobService.filledJob(id, authentication);
        return new ResponseEntity<>(filledJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Optional<JobResponseDto> jobDTO = jobService.getJobById(id);
        jobService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
