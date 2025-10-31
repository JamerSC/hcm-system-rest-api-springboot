package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceDto;
import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceResponseDto;
import com.jamersc.springboot.hcm_api.service.attendance.AttendanceService;
import com.jamersc.springboot.hcm_api.service.employee.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<AttendanceDto>> getAllAttendances(
            @PageableDefault(page = 0, size = 10, sort = "attendanceDate") Pageable pageable) {
        Page<AttendanceDto> attendances = attendanceService.getAllAttendance(pageable);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<Page<AttendanceResponseDto>> getMyAttendances(
            @PageableDefault(page = 0, size = 10, sort = "attendanceDate") Pageable pageable, Authentication authentication) {
        Page<AttendanceResponseDto> myAttendances = attendanceService
                .getMyAttendances(pageable, authentication);
        return new ResponseEntity<>(myAttendances, HttpStatus.OK);
    }


    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponseDto> attendanceCheckIn(Authentication authentication) {
        AttendanceResponseDto attendanceRecord = attendanceService
                .checkIn(authentication);
        return new ResponseEntity<>(attendanceRecord, HttpStatus.CREATED);
    }

    @PatchMapping("/check-out")
    public ResponseEntity<AttendanceResponseDto> attendanceCheckOut(Authentication authentication) {
        AttendanceResponseDto attendanceRecord = attendanceService
                .checkOut(authentication);
        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }
}
