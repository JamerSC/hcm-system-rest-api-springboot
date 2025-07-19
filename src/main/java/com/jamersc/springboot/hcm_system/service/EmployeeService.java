package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    //EmployeeDTO findById(Long id);
    Optional<EmployeeDTO> findById(Long id);
    Employee save(EmployeeCreateDTO employeeDTO);
    Employee update(EmployeeUpdateDTO employeeDTO);
    void deleteEmployeeByID(Long id);
}
