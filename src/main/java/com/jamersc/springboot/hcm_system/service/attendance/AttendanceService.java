package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    List<AttendanceResponseDTO> getAllAttendance();
    List<AttendanceResponseDTO> getMyAttendances(Authentication authentication);
    AttendanceResponseDTO checkIn(Long employeeId);
    AttendanceResponseDTO checkOut(Long employeeId);
}
