package com.jamersc.springboot.hcm_system.service.employee;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeResponseDTO;
import com.jamersc.springboot.hcm_system.dto.profile.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeResponseDTO> getEmployees();
    //EmployeeDTO findById(Long id);
    Optional<EmployeeProfileDTO> findEmployeeProfileById(Long id);
    Optional<EmployeeResponseDTO> findEmployeeById(Long id);
    Optional<EmployeeDTO> findById(Long id);
    EmployeeProfileDTO getEmployeeProfileByUsername(String username);
    EmployeeResponseDTO save(EmployeeCreateDTO employeeDTO, Authentication authentication);
    Employee update(EmployeeUpdateDTO employeeDTO, Authentication authentication);
    Employee patch(EmployeeDTO employeeDTO, Authentication authentication);
    void deleteEmployeeByID(Long id);
}
