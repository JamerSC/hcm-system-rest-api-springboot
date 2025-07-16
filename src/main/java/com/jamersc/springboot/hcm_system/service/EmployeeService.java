package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    EmployeeDTO findById(Long id);
    Employee save(EmployeeDTO employeeDTO);
    void deleteEmployeeByID(Long id);
}
