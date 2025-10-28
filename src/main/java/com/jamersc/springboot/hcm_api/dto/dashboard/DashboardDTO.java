package com.jamersc.springboot.hcm_api.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private Long totalUser;
    private Long totalJobs;
    private Long openJobs;
    private Long totalDepartments;
    private Long totalApplicants;
    private Long totalApplications;
    private Long totalEmployees;
    private Long totalAttendances;
    private Long totalLeaveRequest;
    private Long totalSickLeave;
    private Long totalVacationLeave;
}
