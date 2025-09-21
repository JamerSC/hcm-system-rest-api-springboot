package com.jamersc.springboot.hcm_system.service.attendance;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.mapper.AttendanceMapper;
import com.jamersc.springboot.hcm_system.repository.AttendanceRepository;
import com.jamersc.springboot.hcm_system.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {
        return attendanceMapper.entitiesToResponseDtos(attendanceRepository.findAll());
    }

    @Override
    public AttendanceResponseDTO checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // checked if the employee already checked in
        if (attendanceRepository.findByEmployeeAndAttendanceDate(
                employee, LocalDate.now()).isPresent()) {
            throw new IllegalStateException("Employee has already checked in today");
        }

        Attendance newAttendance = new Attendance();
        newAttendance.setEmployee(employee);
        newAttendance.setAttendanceDate(LocalDate.now());
        newAttendance.setCheckInTime(LocalDateTime.now());

        return attendanceMapper.entityToResponseDto(attendanceRepository.save(newAttendance));
    }

    @Override
    public AttendanceResponseDTO checkOut(Long employeeId) {
        // find employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // find today's attendance record
        Attendance attendance = attendanceRepository.findByEmployeeAndAttendanceDate(
                employee, LocalDate.now()).orElseThrow(()->new RuntimeException("Employee has not checked in yet today"));

        // check if they already checkout
        if (attendance.getCheckOutTime() != null) {
            throw new RuntimeException("Employee has already checked out today");
        }

        attendance.setCheckOutTime(LocalDateTime.now());

        return attendanceMapper.entityToResponseDto(attendanceRepository.save(attendance));
    }


}
