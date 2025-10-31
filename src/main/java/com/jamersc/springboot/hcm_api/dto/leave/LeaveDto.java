package com.jamersc.springboot.hcm_api.dto.leave;

import com.jamersc.springboot.hcm_api.entity.Employee;
import com.jamersc.springboot.hcm_api.entity.LeaveStatus;
import com.jamersc.springboot.hcm_api.entity.LeaveType;
import com.jamersc.springboot.hcm_api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDto {
    private Long id;
    private Employee employee;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
    private Date submittedAt;
    private User approvedBy;
}
