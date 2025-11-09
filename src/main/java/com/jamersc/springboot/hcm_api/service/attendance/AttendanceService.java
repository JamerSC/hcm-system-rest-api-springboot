package com.jamersc.springboot.hcm_api.service.attendance;

import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceDto;
import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface AttendanceService {

    Page<AttendanceDto> getAllAttendance(Pageable pageable);
    Page<AttendanceResponseDto> getMyAttendances(Pageable pageable, Authentication authentication);
    AttendanceResponseDto checkIn(Authentication authentication);
    AttendanceResponseDto checkOut(Authentication authentication);
}
