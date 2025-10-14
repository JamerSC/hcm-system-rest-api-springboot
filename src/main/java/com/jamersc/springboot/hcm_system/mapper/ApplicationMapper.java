package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.application.ApplicationDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApplicationMapper {

    @Mapping(target = "jobDescription", source = "job.description")
    @Mapping(target = "updatedByUsername", source = "updatedBy.username")
    @Mapping(target = "jobPosition", source = "job.title")
    @Mapping(target = "applicantName", source = "applicant.firstName")
    ApplicationDTO entityToDto(Application application);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    Application dtoToEntity(ApplicationDTO dto);

    @Mapping(target = "applicationId", source = "id")
    @Mapping(target = "description", source = "job.description")
    @Mapping(target = "appliedPosition", source = "job.title")
    @Mapping(target = "applicantName", source = "applicant.applicantFullName")
    ApplicationResponseDTO entityToApplicationResponseDto(Application application);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    Application applicationResponseDtoToEntity(ApplicationResponseDTO dto);

    List<ApplicationResponseDTO> entitiesToResponseDtos(List<Application> applications);

    List<Application> responseDtosToEntities(List<ApplicationResponseDTO> dtos);
}
