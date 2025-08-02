package com.jamersc.springboot.hcm_system.service.employee;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    //EmployeeDTO findById(Long id);
    Optional<EmployeeProfileDTO> findEmployeeProfileById(Long id);
    Optional<EmployeeDTO> findById(Long id);
    Employee save(EmployeeCreateDTO employeeDTO);
    Employee update(EmployeeUpdateDTO employeeDTO);
    Employee patch(EmployeeDTO employeeDTO);
    void deleteEmployeeByID(Long id);
}
