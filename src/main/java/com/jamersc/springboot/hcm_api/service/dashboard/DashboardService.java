package com.jamersc.springboot.hcm_api.service.dashboard;

import com.jamersc.springboot.hcm_api.dto.dashboard.DashboardDto;
import com.jamersc.springboot.hcm_api.entity.JobStatus;
import com.jamersc.springboot.hcm_api.entity.LeaveType;
import com.jamersc.springboot.hcm_api.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private static final Logger log = LoggerFactory.getLogger(DashboardService.class);
    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private  final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;

    public DashboardService(UserRepository userRepository, ApplicantRepository applicantRepository, ApplicationRepository applicationRepository, EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository, AttendanceRepository attendanceRepository, LeaveRepository leaveRepository) {
        this.userRepository = userRepository;
        this.applicantRepository = applicantRepository;
        this.applicationRepository = applicationRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
    }

    @Transactional
    public DashboardDto getDashboardData() {

        Long totalUser = userRepository.count();
        Long totalJobs = jobRepository.count();
        Long openJobs = jobRepository.countByStatus(JobStatus.OPEN); // custom query
        Long totalDepartments = departmentRepository.count();
        Long totalApplicants = applicantRepository.count();
        Long totalApplications = applicationRepository.count();
        Long totalEmployees = employeeRepository.count();
        Long totalAttendance = attendanceRepository.count();
        Long totalLeaveRequest = leaveRepository.count();
        Long totalSickLeave = leaveRepository.countByLeaveType(LeaveType.SICK_LEAVE);
        Long totalVacationLeave = leaveRepository.countByLeaveType(LeaveType.VACATION_LEAVE);

        return new DashboardDto(
                totalUser,
                totalJobs,
                openJobs,
                totalDepartments,
                totalApplicants,
                totalApplications,
                totalEmployees,
                totalAttendance,
                totalLeaveRequest,
                totalSickLeave,
                totalVacationLeave
        );
    }
}
