package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.leave.LeaveCreateDto;
import com.jamersc.springboot.hcm_api.dto.leave.LeaveDto;
import com.jamersc.springboot.hcm_api.dto.leave.LeaveResponseDto;
import com.jamersc.springboot.hcm_api.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LeaveMapper {

    LeaveDto entityToDto(Leave leave);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Leave dtoToEntity(LeaveDto dto);

    @Mapping(target = "approvedBy", source = "approvedBy.employee.job.title")
    @Mapping(target = "employeeFullName", source = "employee.employeeFullName")
    LeaveResponseDto entityToResponseDto(Leave leave);

    @Mapping(target = "approvedBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Leave responseDtoToEntity(LeaveResponseDto responseDTO);

    LeaveCreateDto entityToCreateDto(Leave leave);
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "submittedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    Leave createDtoToEntity(LeaveCreateDto dto);


    List<LeaveResponseDto> entitiesToResponseDtos(List<Leave> leaves);

}
