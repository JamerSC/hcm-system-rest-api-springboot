package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    List<Attendance> getAllAttendances();
    Attendance checkIn(Long employeeId);
    Attendance checkOut(Long employeeId);
}
