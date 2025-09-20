package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.profile.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.service.attendance.AttendanceService;
import com.jamersc.springboot.hcm_system.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<Attendance> attendanceCheckIn(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeProfileDTO employee = employeeService.getEmployeeProfileByUsername(userDetails.getUsername());

        Attendance attendanceRecord = attendanceService.checkIn(employee.getId());

        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }

    @PatchMapping("/check-out")
    public ResponseEntity<Attendance> attendanceCheckOut(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeProfileDTO employee = employeeService.getEmployeeProfileByUsername(userDetails.getUsername());

        Attendance attendanceRecord = attendanceService.checkOut(employee.getId());

        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }
}
