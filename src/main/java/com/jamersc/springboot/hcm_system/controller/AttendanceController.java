package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.service.attendance.AttendanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public String checkIn() {return null;}

    public String checkOut() {return null;}
}
