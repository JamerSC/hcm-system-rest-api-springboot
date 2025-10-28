package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_api.dto.job.JobDTO;
import com.jamersc.springboot.hcm_api.dto.job.JobPatchDTO;
import com.jamersc.springboot.hcm_api.dto.job.JobResponseDTO;
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
    public ResponseEntity<Page<JobDTO>> getAllJobs(
            @PageableDefault(page = 0, size = 10, sort = "title") Pageable pageable) {
        Page<JobDTO> jobs = jobService.getAllJob(pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<JobResponseDTO>> getJobById(@PathVariable Long id) {
        Optional<JobResponseDTO> jobResponse = jobService.getJobById(id);
        return ResponseEntity.ok(jobResponse);
    }

    @PostMapping("/")
    public ResponseEntity<JobResponseDTO> createJob(
            @Valid @RequestBody JobCreateDTO createDTO,
            Authentication authentication) {
        JobResponseDTO jobResponseDTO = jobService.save(createDTO, authentication);
        return new ResponseEntity<>(jobResponseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobResponseDTO> patchJob(
            @PathVariable Long id, @RequestBody JobPatchDTO dto, Authentication authentication) {
        JobResponseDTO patchedJob = jobService.patchJob(id, dto, authentication);
        return new ResponseEntity<>(patchedJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/open")
    public ResponseEntity<JobResponseDTO> postJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDTO postJob = jobService.postJob(id, authentication);
        return new ResponseEntity<>(postJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponseDTO> closeJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDTO closeJob = jobService.closeJob(id, authentication);
        return new ResponseEntity<>(closeJob, HttpStatus.OK);
    }

    @PatchMapping("/{id}/filled")
    public ResponseEntity<JobResponseDTO> filledJob(
            @PathVariable Long id, Authentication authentication) {
        JobResponseDTO filledJob = jobService.filledJob(id, authentication);
        return new ResponseEntity<>(filledJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Optional<JobResponseDTO> jobDTO = jobService.getJobById(id);
        jobService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
