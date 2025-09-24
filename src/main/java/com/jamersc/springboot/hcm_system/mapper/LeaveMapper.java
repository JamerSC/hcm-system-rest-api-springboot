package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LeaveMapper {

    LeaveRequestDTO entityToDto(LeaveRequest leaveRequest);
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LeaveRequest dtoToEntity(LeaveRequestDTO dto);

    @Mapping(target = "employeeFullName", ignore = true)
    LeaveRequestResponseDTO entityToResponseDto(LeaveRequest leaveRequest);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LeaveRequest responseDtoToEntity(LeaveRequestResponseDTO responseDTO);

    LeaveRequestCreateDTO entityToCreateDto(LeaveRequest leaveRequest);
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "submittedAt", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    LeaveRequest createDtoToEntity(LeaveRequestCreateDTO dto);


    List<LeaveRequestResponseDTO> entitiesToResponseDtos(List<LeaveRequest> leaveRequests);

}
