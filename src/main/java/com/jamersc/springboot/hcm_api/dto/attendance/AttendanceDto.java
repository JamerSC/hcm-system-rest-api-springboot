package com.jamersc.springboot.hcm_api.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.hcm_api.entity.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Long attendanceId;
    private LocalDate attendanceDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private LocalDateTime checkInTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private LocalDateTime checkOutTime;
    private Long employeeId;
    private String employeeName;
    private String jobTitle;
    private String department;
    private AttendanceStatus status;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date createdAt;
    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
    private Date updatedAt;
}
