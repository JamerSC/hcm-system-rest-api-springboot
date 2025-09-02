package com.jamersc.springboot.hcm_system.service.dashboard;

import com.jamersc.springboot.hcm_system.dto.dashboard.DashboardDTO;
import com.jamersc.springboot.hcm_system.entity.JobStatus;
import com.jamersc.springboot.hcm_system.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardService(ApplicantRepository applicantRepository, ApplicationRepository applicationRepository, EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.applicantRepository = applicantRepository;
        this.applicationRepository = applicationRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public DashboardDTO getDashboardData() {

        Long totalJobs = jobRepository.count();
        Long openJobs = jobRepository.countByStatus(JobStatus.OPEN); // custom query
        Long totalDepartments = departmentRepository.count();
        Long totalApplicants = applicantRepository.count();
        Long totalApplications = applicationRepository.count();
        Long totalEmployees = employeeRepository.count();

        return new DashboardDTO(
                totalJobs,
                openJobs,
                totalDepartments,
                totalApplicants,
                totalApplications,
                totalEmployees
        );
    }
}
