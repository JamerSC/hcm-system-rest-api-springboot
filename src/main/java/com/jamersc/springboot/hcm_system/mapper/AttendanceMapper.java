package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceDTO;
import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttendanceMapper {

    @Mapping(target = "createdBy", source = "createdBy.employee.job.title")
    @Mapping(target = "updatedBy", source = "updatedBy.employee.job.title")
    @Mapping(target = "jobTitle", source = "employee.job.title")
    @Mapping(target = "department", source = "employee.job.department.name")
    @Mapping(target = "attendanceId", source = "id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeName", source = "employee.employeeFullName")
    AttendanceDTO entityToDto(Attendance attendance);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "checkOutTime", ignore = true)
    @Mapping(target = "checkInTime", ignore = true)
    @Mapping(target = "attendanceDate", ignore = true)
    Attendance dtoToEntity(AttendanceDTO dto);

    @Mapping(target = "jobTitle", source = "employee.job.title")
    @Mapping(target = "department", source = "employee.job.department.name")
    @Mapping(target = "attendanceId", source = "id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeName", source = "employee.employeeFullName")
    AttendanceResponseDTO entityToResponseDto(Attendance attendance);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", ignore = true)
    Attendance responseDtoToEntity(AttendanceResponseDTO dto);

    List<AttendanceDTO> entitiesToDtos(List<Attendance> attendances);

    List<AttendanceResponseDTO> entitiesToResponseDtos(List<Attendance> attendances);
}
