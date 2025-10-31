package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.department.DepartmentCreateDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentPatchDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentResponseDto;
import com.jamersc.springboot.hcm_api.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentDto entityToDeptDto(Department dept);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Department deptDtoToEntity(DepartmentDto dto);

    // create department mapper
    DepartmentCreateDto entityToCreateDto(Department dept);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Department createDtoToEntity(DepartmentCreateDto dto);

    // response department mapper
    // convert department entity to the response dto
    @Mapping(target = "updatedBy", source = "updatedBy.employee.job.title")
    @Mapping(target = "createdBy", source = "createdBy.employee.job.title")
    DepartmentResponseDto entityToDepartmentResponseDto(Department dept);

    DepartmentPatchDto entityToPatchDto(Department dept);


    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Department patchDtoToEntity(DepartmentPatchDto dto);

    List<DepartmentDto> entitiesToDeptDtos(List<Department> departments);

    List<DepartmentResponseDto> entitiesToDeptResponseDto(List<Department> departments);

    List<Department> deptDtosToEntities(List<DepartmentDto> deptDtos);
}
