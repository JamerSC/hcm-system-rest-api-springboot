package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    Employee findById(Long id);
    Employee save(Employee employee);
    void deleteEmployeeByID(Long id);
}
