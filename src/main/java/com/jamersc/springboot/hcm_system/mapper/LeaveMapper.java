package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LeaveMapper {

    LeaveDTO entityToDto(Leave leave);
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Leave dtoToEntity(LeaveDTO dto);

    @Mapping(target = "employeeFullName", ignore = true)
    LeaveResponseDTO entityToResponseDto(Leave leave);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Leave responseDtoToEntity(LeaveResponseDTO responseDTO);

    LeaveCreateDTO entityToCreateDto(Leave leave);
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "submittedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    Leave createDtoToEntity(LeaveCreateDTO dto);


    List<LeaveResponseDTO> entitiesToResponseDtos(List<Leave> leaves);

}
