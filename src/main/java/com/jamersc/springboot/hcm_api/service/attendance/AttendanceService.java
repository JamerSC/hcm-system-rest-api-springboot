package com.jamersc.springboot.hcm_api.service.attendance;

import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceDTO;
import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface AttendanceService {

    Page<AttendanceDTO> getAllAttendance(Pageable pageable);
    Page<AttendanceResponseDTO> getMyAttendances(Pageable pageable, Authentication authentication);
    AttendanceResponseDTO checkIn(Authentication authentication);
    AttendanceResponseDTO checkOut(Authentication authentication);
}
