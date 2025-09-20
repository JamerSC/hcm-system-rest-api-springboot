package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    List<Attendance> getAllAttendances();
    AttendanceResponseDTO checkIn(Long employeeId);
    AttendanceResponseDTO checkOut(Long employeeId);
}
