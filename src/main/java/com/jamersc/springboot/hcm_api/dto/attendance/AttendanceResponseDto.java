package com.jamersc.springboot.hcm_api.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseDto {
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
    //private AttendanceStatus status;
}
