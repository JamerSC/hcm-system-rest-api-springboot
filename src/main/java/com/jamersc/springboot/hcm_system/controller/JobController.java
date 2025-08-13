package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.repository.DepartmentRepository;
import com.jamersc.springboot.hcm_system.service.job.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    private ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJob();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<JobDTO>> getJobById(@PathVariable Long id) {
        Optional<JobDTO> jobResponse = jobService.getJobById(id);
        return ResponseEntity.ok(jobResponse);
    }

    @PostMapping("/")
    private ResponseEntity<JobResponseDTO> createJob(
            @Valid @RequestBody JobCreateDTO createDTO,
            Authentication authentication) {
        JobResponseDTO jobResponseDTO = jobService.save(createDTO, authentication);
        return new ResponseEntity<>(jobResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Optional<JobDTO> jobDTO = jobService.getJobById(id);
        jobService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
