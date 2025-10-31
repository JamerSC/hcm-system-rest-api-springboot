package com.jamersc.springboot.hcm_api.service.employee;

import com.jamersc.springboot.hcm_api.dto.employee.*;
import com.jamersc.springboot.hcm_api.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface EmployeeService {
    Page<EmployeeResponseDto> getAllEmployee(Pageable pageable);
    //EmployeeDTO findById(Long id);
    Optional<EmployeeProfileDto> findEmployeeProfileById(Long id);
    Optional<EmployeeResponseDto> findEmployeeById(Long id);
    Optional<EmployeeDto> findById(Long id);
    EmployeeProfileDto getMyEmployeeProfile(Authentication authentication);
    EmployeeResponseDto save(EmployeeCreateDto employeeDTO, Authentication authentication);
    Employee update(EmployeeUpdateDto employeeDTO, Authentication authentication);
    EmployeeResponseDto patchEmployee(Long id, EmployeePatchDto dto, Authentication authentication);
    void deleteEmployeeByID(Long id);
}
