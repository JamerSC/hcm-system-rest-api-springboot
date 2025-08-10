package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.profile.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // This makes it a Spring-managed bean
public interface EmployeeMapper {

    // employee
    EmployeeDTO entityToDto(Employee employee);
    @Mapping(target = "user", ignore = true)
    Employee dtoToEntity(EmployeeDTO dto);

    // employee profile
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "roles", expression = "java(mapRoles(employee.getUser().getRoles()))")
    EmployeeProfileDTO entityToProfileDto(Employee employee);

    default Set<String> mapRoles(Set<Role> roles) {
        if (roles==null) {
            return null;
        }
        return roles.stream()
                .map(Role::getRoleName).collect(Collectors.toSet());
    }

    // employee create
    EmployeeCreateDTO createEntityToDto(Employee employee);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Employee createDtoToEntity(EmployeeCreateDTO dto);

    // employee update
    EmployeeUpdateDTO updateEntityToDto(Employee employee);

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Employee updateDtoToEntity(EmployeeUpdateDTO dto);

    // List collection of employee
    List<EmployeeDTO> entitiesToDtos(List<Employee> employees);
    List<Employee> dtosToEntities(List<EmployeeDTO> dtos);

}
