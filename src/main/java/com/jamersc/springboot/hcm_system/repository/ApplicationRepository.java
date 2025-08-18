package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // custom query to check if an applicant has already applied for a specific job
    Optional<Application> findByApplicantAndJob(Applicant applicant, Job job);

    //    List<Application> findApplicantApplicationsById(Long id);

    @Query("SELECT a FROM Application a JOIN FETCH a.applicant b WHERE b.id = :id")
    List<Application> findApplicantApplicationsById(@Param("id") Long id);
}
