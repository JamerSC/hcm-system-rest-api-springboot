package com.jamersc.springboot.hcm_system.service.employee;

import com.jamersc.springboot.hcm_system.dto.employee.*;
import com.jamersc.springboot.hcm_system.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface EmployeeService {
    Page<EmployeeResponseDTO> getAllEmployee(Pageable pageable);
    //EmployeeDTO findById(Long id);
    Optional<EmployeeProfileDTO> findEmployeeProfileById(Long id);
    Optional<EmployeeResponseDTO> findEmployeeById(Long id);
    Optional<EmployeeDTO> findById(Long id);
    EmployeeProfileDTO getMyEmployeeProfile(Authentication authentication);
    EmployeeResponseDTO save(EmployeeCreateDTO employeeDTO, Authentication authentication);
    Employee update(EmployeeUpdateDTO employeeDTO, Authentication authentication);
    EmployeeResponseDTO patchEmployee(Long id, EmployeePatchDTO dto, Authentication authentication);
    void deleteEmployeeByID(Long id);
}
