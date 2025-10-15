package com.jamersc.springboot.hcm_system.service.job;

import com.jamersc.springboot.hcm_system.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Department;
import com.jamersc.springboot.hcm_system.entity.Job;
import com.jamersc.springboot.hcm_system.entity.JobStatus;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.JobMapper;
import com.jamersc.springboot.hcm_system.repository.DepartmentRepository;
import com.jamersc.springboot.hcm_system.repository.JobRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Page<JobDTO> getAllJob(Pageable pageable) {
        Page<Job> jobs = jobRepository.findAll(pageable);
        return jobs.map(jobMapper::entityToJobDto);
    }

    @Override
    public Page<JobResponseDTO> getOpenJobs(Pageable pageable) {
        Page<Job> openJobs = jobRepository.findByStatus(pageable, JobStatus.OPEN);
        return openJobs.map(jobMapper::entityToJobResponseDto);
    }

    @Override
    public Optional<JobResponseDTO> getJobById(Long id) {
        return Optional.of(jobRepository.findById(id)
                        .map(jobMapper::entityToJobResponseDto))
                .orElseThrow(() -> new RuntimeException("Job Id not found"));
    }

    @Override
    public JobResponseDTO save(JobCreateDTO dto, Authentication authentication) {
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
    public JobResponseDTO postJob(Long id, Authentication authentication) {
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
    public JobResponseDTO filledJob(Long id, Authentication authentication) {
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
    public JobResponseDTO closeJob(Long id, Authentication authentication) {
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
