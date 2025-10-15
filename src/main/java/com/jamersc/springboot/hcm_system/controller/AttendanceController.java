package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceDTO;
import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.dto.profile.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.service.attendance.AttendanceService;
import com.jamersc.springboot.hcm_system.service.employee.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<AttendanceDTO>> getAllAttendances(
            @PageableDefault(page = 0, size = 10, sort = "attendanceDate") Pageable pageable) {
        Page<AttendanceDTO> attendances = attendanceService.getAllAttendance(pageable);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<Page<AttendanceResponseDTO>> getMyAttendances(
            @PageableDefault(page = 0, size = 10, sort = "attendanceDate") Pageable pageable, Authentication authentication) {
        Page<AttendanceResponseDTO> myAttendances = attendanceService
                .getMyAttendances(pageable, authentication);
        return new ResponseEntity<>(myAttendances, HttpStatus.OK);
    }


    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponseDTO> attendanceCheckIn(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeProfileDTO employee = employeeService.getEmployeeProfileByUsername(userDetails.getUsername());

        AttendanceResponseDTO attendanceRecord = attendanceService.checkIn(employee.getId());

        return new ResponseEntity<>(attendanceRecord, HttpStatus.CREATED);
    }

    @PatchMapping("/check-out")
    public ResponseEntity<AttendanceResponseDTO> attendanceCheckOut(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeProfileDTO employee = employeeService.getEmployeeProfileByUsername(userDetails.getUsername());

        AttendanceResponseDTO attendanceRecord = attendanceService.checkOut(employee.getId());

        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }
}
