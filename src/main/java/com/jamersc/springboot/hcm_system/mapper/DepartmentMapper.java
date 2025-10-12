package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.department.DepartmentCreateDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentDTO entityToDeptDto(Department dept);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    //q@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Department deptDtoToEntity(DepartmentDTO dto);

    // create department mapper
    DepartmentCreateDTO entityToCreateDto(Department dept);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Department createDtoToEntity(DepartmentCreateDTO dto);

    // response department mapper
    // convert department entity to the response dto
    @Mapping(target = "updatedBy", source = "updatedBy.employee.job.title")
    @Mapping(target = "createdBy", source = "createdBy.employee.job.title")
    DepartmentResponseDTO entityToDepartmentResponseDto(Department dept);

    List<DepartmentDTO> entitiesToDeptDtos(List<Department> departments);

    List<DepartmentResponseDTO> entitiesToDeptResponseDto(List<Department> departments);

    List<Department> deptDtosToEntities(List<DepartmentDTO> deptDtos);
}
