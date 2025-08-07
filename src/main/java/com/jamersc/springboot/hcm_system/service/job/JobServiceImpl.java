package com.jamersc.springboot.hcm_system.service.job;

import com.jamersc.springboot.hcm_system.entity.Job;
import com.jamersc.springboot.hcm_system.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return Optional.of(jobRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Job Id not found"));
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteById(Long id) {

    }
}
