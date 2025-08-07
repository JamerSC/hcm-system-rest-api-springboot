package com.jamersc.springboot.hcm_system.service.job;

import com.jamersc.springboot.hcm_system.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> getAllJob();
    Optional<Job> getJobById(Long id);
    Job save(Job job);
    void deleteById(Long id);
}
