package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // custom query to check if an applicant has already applied for a specific job
    Optional<Application> findByApplicantAndJob(Applicant applicant, Job job);
}
