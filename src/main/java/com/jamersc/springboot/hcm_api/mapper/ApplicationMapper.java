package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.application.ApplicationDTO;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationUpdateDTO;
import com.jamersc.springboot.hcm_api.entity.Application;
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

    @Mapping(target = "source", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "proposedSalary", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "linkedInProfile", ignore = true)
    @Mapping(target = "expectedSalary", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "degree", ignore = true)
    @Mapping(target = "availability", ignore = true)
    @Mapping(target = "applicationSummary", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    Application dtoToEntity(ApplicationDTO dto);


    @Mapping(target = "applicationId", source = "id")
    @Mapping(target = "description", source = "job.description")
    @Mapping(target = "appliedPosition", source = "job.title")
    @Mapping(target = "applicantName", source = "applicant.applicantFullName")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    ApplicationUpdateDTO entityToUpdateDto(Application application);

    @Mapping(target = "job", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Application updateDtoToEntity(ApplicationUpdateDTO dto);

    @Mapping(target = "applicationId", source = "id")
    @Mapping(target = "description", source = "job.description")
    @Mapping(target = "appliedPosition", source = "job.title")
    @Mapping(target = "applicantName", source = "applicant.applicantFullName")
    ApplicationResponseDTO entityToApplicationResponseDto(Application application);

    @Mapping(target = "source", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "proposedSalary", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "linkedInProfile", ignore = true)
    @Mapping(target = "expectedSalary", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "degree", ignore = true)
    @Mapping(target = "availability", ignore = true)
    @Mapping(target = "applicationSummary", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    Application applicationResponseDtoToEntity(ApplicationResponseDTO dto);

    List<ApplicationResponseDTO> entitiesToResponseDtos(List<Application> applications);

    List<Application> responseDtosToEntities(List<ApplicationResponseDTO> dtos);
}
