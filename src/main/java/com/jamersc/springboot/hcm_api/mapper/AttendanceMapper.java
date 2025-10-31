package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceDto;
import com.jamersc.springboot.hcm_api.dto.attendance.AttendanceResponseDto;
import com.jamersc.springboot.hcm_api.entity.Attendance;
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
    AttendanceDto entityToDto(Attendance attendance);

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
    Attendance dtoToEntity(AttendanceDto dto);

    @Mapping(target = "jobTitle", source = "employee.job.title")
    @Mapping(target = "department", source = "employee.job.department.name")
    @Mapping(target = "attendanceId", source = "id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeName", source = "employee.employeeFullName")
    AttendanceResponseDto entityToResponseDto(Attendance attendance);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", ignore = true)
    Attendance responseDtoToEntity(AttendanceResponseDto dto);

    List<AttendanceDto> entitiesToDtos(List<Attendance> attendances);

    List<AttendanceResponseDto> entitiesToResponseDtos(List<Attendance> attendances);
}
