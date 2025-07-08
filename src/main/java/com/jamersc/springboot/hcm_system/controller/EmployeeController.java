package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.model.Employee;
import com.jamersc.springboot.hcm_system.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public List<Employee> getEmployees() {

        return employeeService.getEmployees();
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        //Employee theEmployee = employeeService.findById(id);
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Employee tempEmployee = employeeService.findById(id);
        employeeService.deleteEmployeeByID(id);
        return "Deleted employee id - " + id;
    }
}
