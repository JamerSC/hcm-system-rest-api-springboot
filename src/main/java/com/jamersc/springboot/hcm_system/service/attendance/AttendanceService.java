package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    Page<AttendanceResponseDTO> getAllAttendance(Pageable pageable);
    Page<AttendanceResponseDTO> getMyAttendances(Pageable pageable, Authentication authentication);
    AttendanceResponseDTO checkIn(Long employeeId);
    AttendanceResponseDTO checkOut(Long employeeId);
}
