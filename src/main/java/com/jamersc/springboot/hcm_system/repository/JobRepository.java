package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Job;
import com.jamersc.springboot.hcm_system.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);

    Long countByStatus(JobStatus jobStatus);

//    @Query("SELECT j FROM Job j WHERE j.status IN ('OPEN','CLOSED')")
//    @Query("SELECT j FROM Job j WHERE j.status = 'OPEN'")
//    List<Job> findJobsWithStatusOpen();
}
