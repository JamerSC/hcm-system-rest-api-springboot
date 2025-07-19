package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // This makes it a Spring-managed bean
public interface EmployeeMapper {

    // employee
    EmployeeDTO entityToDto(Employee employee);
    Employee dtoToEntity(EmployeeDTO dto);

    // employee create
    EmployeeCreateDTO createEntityToDto(Employee employee);
    Employee createDtoToEntity(EmployeeCreateDTO dto);

    // employee update
    EmployeeUpdateDTO updateEntityToDto(Employee employee);
    Employee updateDtoToEntity(EmployeeUpdateDTO dto);

    // List collection of employee
    List<EmployeeDTO> entitiesToDtos(List<Employee> employees);
    List<Employee> dtosToEntities(List<EmployeeDTO> dtos);

}
