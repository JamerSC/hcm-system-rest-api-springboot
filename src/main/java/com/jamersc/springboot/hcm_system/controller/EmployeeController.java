package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/employees")
    public List<Employee> getEmployees() {

        List<Employee> employees;

        employees = Arrays.asList(
                        new Employee(1L, "John", "Doe", "john@mail.com", "Assistant Admin", "Admin Department", LocalDate.of(2024,1, 1), 30000.00),
                        new Employee(2L, "Mary", "Public", "mary@mail.com", "Assistant Admin", "Admin Department", LocalDate.of(2024,6, 1), 30000.00),
                        new Employee(3L, "Susan", "Roses", "susan@mail.com", "Assistant Admin", "Admin Department", LocalDate.of(2025,1, 1), 30000.00)
                        );

        return employees;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
