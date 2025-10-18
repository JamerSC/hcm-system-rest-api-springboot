package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceDTO;
import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    Page<AttendanceDTO> getAllAttendance(Pageable pageable);
    Page<AttendanceResponseDTO> getMyAttendances(Pageable pageable, Authentication authentication);
    AttendanceResponseDTO checkIn(Authentication authentication);
    AttendanceResponseDTO checkOut(Authentication authentication);
}
