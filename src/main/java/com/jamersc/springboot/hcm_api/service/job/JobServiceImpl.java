package com.jamersc.springboot.hcm_api.service.job;

import com.jamersc.springboot.hcm_api.dto.job.JobCreateDto;
import com.jamersc.springboot.hcm_api.dto.job.JobDto;
import com.jamersc.springboot.hcm_api.dto.job.JobPatchDto;
import com.jamersc.springboot.hcm_api.dto.job.JobResponseDto;
import com.jamersc.springboot.hcm_api.entity.Department;
import com.jamersc.springboot.hcm_api.entity.Job;
import com.jamersc.springboot.hcm_api.entity.JobStatus;
import com.jamersc.springboot.hcm_api.entity.User;
import com.jamersc.springboot.hcm_api.mapper.JobMapper;
import com.jamersc.springboot.hcm_api.repository.DepartmentRepository;
import com.jamersc.springboot.hcm_api.repository.JobRepository;
import com.jamersc.springboot.hcm_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;
    private final DepartmentRepository departmentRepository;

    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository, JobMapper jobMapper, DepartmentRepository departmentRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.jobMapper = jobMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<JobDto> getAllJob(Pageable pageable) {
        Page<Job> jobs = jobRepository.findAll(pageable);
        return jobs.map(jobMapper::entityToJobDto);
    }

    @Override
    public Page<JobResponseDto> getOpenJobs(Pageable pageable) {
        Page<Job> openJobs = jobRepository.findByStatus(pageable, JobStatus.OPEN);
        return openJobs.map(jobMapper::entityToJobResponseDto);
    }

    @Override
    public Optional<JobResponseDto> getJobById(Long id) {
        return Optional.of(jobRepository.findById(id)
                        .map(jobMapper::entityToJobResponseDto))
                .orElseThrow(() -> new RuntimeException("Job Id not found"));
    }

    @Override
    public JobResponseDto save(JobCreateDto dto, Authentication authentication) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department id not found!"));
        
        User currentUser = getUser(authentication);

        Job job = jobMapper.jobCreateDtoToEntity(dto);
        job.setDepartment(department);
        job.setCreatedBy(currentUser);
        job.setUpdatedBy(currentUser);

        Job saveJob = jobRepository.save(job);

        return jobMapper.entityToJobResponseDto(saveJob);
    }

    @Override
    public JobResponseDto postJob(Long id, Authentication authentication) {
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found"));

        User currentUser = getUser(authentication);

        job.setStatus(JobStatus.OPEN);
        job.setPostedBy(currentUser);
        job.setUpdatedBy(currentUser);

        Job openJob = jobRepository.save(job);

        return jobMapper.entityToJobResponseDto(openJob);
    }

    @Override
    public JobResponseDto patchJob(Long id, JobPatchDto dto, Authentication authentication) {
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found"));

        User currentUser = getUser(authentication);

        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            job.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            job.setTitle(dto.getDescription());
        }
        if (dto.getRequirements() != null && !dto.getRequirements().isBlank()) {
            job.setTitle(dto.getRequirements());
        }
        if (dto.getLocation() != null && !dto.getLocation().isBlank()) {
            job.setTitle(dto.getRequirements());
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(()-> new RuntimeException("Department not found"));
            job.setDepartment(department);
        }

        job.setUpdatedBy(currentUser);
        Job patchedJob = jobRepository.save(job);

        return jobMapper.entityToJobResponseDto(patchedJob);
    }

    @Override
    public JobResponseDto filledJob(Long id, Authentication authentication) {
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found"));

        User currentUser = getUser(authentication);

        job.setStatus(JobStatus.FILLED);
        job.setPostedBy(null);
        job.setUpdatedBy(currentUser);

        Job openJob = jobRepository.save(job);

        return jobMapper.entityToJobResponseDto(openJob);
    }

    @Override
    public JobResponseDto closeJob(Long id, Authentication authentication) {
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found"));

        User currentUser = getUser(authentication);

        job.setStatus(JobStatus.CLOSED);
        job.setPostedBy(null);
        job.setUpdatedBy(currentUser);

        Job openJob = jobRepository.save(job);

        return jobMapper.entityToJobResponseDto(openJob);
    }


    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("Authenticated user not found."));
    }
}
