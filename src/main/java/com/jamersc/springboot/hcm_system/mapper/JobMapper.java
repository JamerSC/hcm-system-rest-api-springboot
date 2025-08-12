package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.job.JobCreateDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobDTO;
import com.jamersc.springboot.hcm_system.dto.job.JobResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobMapper {

    JobDTO entityToJobDto(Job job);

    @Mapping(target = "updatedBy", source = "")
    @Mapping(target = "updatedAt", source = "")
    @Mapping(target = "title", source = "")
    @Mapping(target = "status", source = "")
    @Mapping(target = "requirements", source = "")
    @Mapping(target = "postedDate", source = "")
    @Mapping(target = "postedBy", source = "")
    @Mapping(target = "location", source = "")
    @Mapping(target = "id", source = "")
    @Mapping(target = "description", source = "")
    @Mapping(target = "department", source = "")
    @Mapping(target = "createdBy", source = "")
    @Mapping(target = "createdAt", source = "")
    Job jobDtoToEntity(JobDTO dto);

    // create job entity
    JobCreateDTO entityToJobCreateDto(Job job);

    @Mapping(target = "updatedBy", source = "")
    @Mapping(target = "updatedAt", source = "")
    @Mapping(target = "title", source = "")
    @Mapping(target = "status", source = "")
    @Mapping(target = "requirements", source = "")
    @Mapping(target = "postedDate", source = "")
    @Mapping(target = "postedBy", source = "")
    @Mapping(target = "location", source = "")
    @Mapping(target = "id", source = "")
    @Mapping(target = "description", source = "")
    @Mapping(target = "department", source = "")
    @Mapping(target = "createdBy", source = "")
    @Mapping(target = "createdAt", source = "")
    Job jobCreateDtoToEntity(JobCreateDTO dto);

    @Mapping(target = "updatedByUsername", source = "updatedBy.username")
    @Mapping(target = "createdByUsername", source = "createdBy.username")
    JobResponseDTO entityToJobResponseDto(Job job);

    @Mapping(target = "updatedBy", source = "")
    @Mapping(target = "postedBy", source = "")
    @Mapping(target = "id", source = "")
    @Mapping(target = "createdBy", source = "")
    Job jobResponseDtoToEntity(JobResponseDTO dto);

    List<JobDTO> entitiesToJobDtos(List<Job> jobs);

    List<Job> jobDtosToEntities(List<JobDTO> dtos);
}
