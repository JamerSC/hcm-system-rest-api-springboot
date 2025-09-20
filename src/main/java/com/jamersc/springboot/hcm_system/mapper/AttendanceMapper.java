package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.attendance.AttendanceResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttendanceMapper {

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "employeeFullName", ignore = true)
    AttendanceResponseDTO entityToResponseDto(Attendance attendance);


    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "attendanceStatus", ignore = true)
    Attendance responseDtoToEntity(AttendanceResponseDTO dto);

}
