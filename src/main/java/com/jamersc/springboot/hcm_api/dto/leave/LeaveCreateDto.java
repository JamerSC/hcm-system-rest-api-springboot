package com.jamersc.springboot.hcm_api.dto.leave;

import com.jamersc.springboot.hcm_api.entity.LeaveType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveCreateDTO {
//    @NotBlank(message = "Employee is required")
//    private Long id;
    @NotNull(message = "Leave type is required")
    private LeaveType leaveType;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    @NotBlank(message = "Reason is required")
    private String reason;
//    private LeaveStatus status = LeaveStatus.PENDING;
}
