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


    @Mapping(target = "department", source = "department.name")
    @Mapping(target = "updatedBy", source = "updatedBy.employee.job.title")
    @Mapping(target = "createdBy", source = "createdBy.employee.job.title")
    JobDTO entityToJobDto(Job job);

    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "postedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Job jobDtoToEntity(JobDTO dto);

    // create job entity
    @Mapping(target = "departmentId", source = "department.id")
    JobCreateDTO entityToJobCreateDto(Job job);

    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "postedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Job jobCreateDtoToEntity(JobCreateDTO dto);

    @Mapping(target = "jobId", source = "id")
    @Mapping(target = "postedBy", source = "postedBy.employee.job.title")
    @Mapping(target = "department", source = "department.name")
    JobResponseDTO entityToJobResponseDto(Job job);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "postedBy", ignore = true)
    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Job jobResponseDtoToEntity(JobResponseDTO dto);

    List<JobDTO> entitiesToJobDtos(List<Job> jobs);

    List<Job> jobDtosToEntities(List<JobDTO> dtos);
}
