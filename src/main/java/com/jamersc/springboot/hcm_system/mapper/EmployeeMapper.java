package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // This makes it a Spring-managed bean
public interface EmployeeMapper {

    EmployeeDTO entityToDto(Employee employee);
    Employee dtoToEntity(EmployeeDTO dto);
    List<EmployeeDTO> entitiesToDtos(List<Employee> employees);
    List<Employee> dtosToEntities(List<EmployeeDTO> dtos);
}
