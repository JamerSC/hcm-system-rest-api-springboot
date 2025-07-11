package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.exception.EmployeeIDNotAllowedException;
import com.jamersc.springboot.hcm_system.exception.EmployeeNotFoundException;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.mapper.EmployeeMapper;
import com.jamersc.springboot.hcm_system.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    @Override
    public List<EmployeeDTO> getEmployees() {
        return employeeMapper.entitiesToDtos(employeeRepository.findAll());
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null) {
            return employee;
        }
        throw new EmployeeIDNotAllowedException("Employee id not found. - " + id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByID(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found - " + id));
        employeeRepository.deleteById(id);
    }
}
