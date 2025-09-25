package com.jamersc.springboot.hcm_system.dto.leave;

import com.jamersc.springboot.hcm_system.entity.LeaveStatus;
import com.jamersc.springboot.hcm_system.entity.LeaveType;
import com.jamersc.springboot.hcm_system.entity.User;
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
public class LeaveResponseDTO {
    private Long id;
    private String employeeFullName;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
    private Date submittedAt;
    private User approvedBy;
}
