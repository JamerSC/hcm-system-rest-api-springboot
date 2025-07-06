package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee findById(Long id);
    Employee save(Employee employee);
    void deleteEmployeeByID(Long id);
}
