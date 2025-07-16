package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.save(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
        // Employee theEmployee = employeeService.findById(id);
        // return employeeService.findById(id);
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        EmployeeDTO tempEmployee = employeeService.findById(id);
        employeeService.deleteEmployeeByID(id);
        return "Deleted employee id - " + id;
    }
}
