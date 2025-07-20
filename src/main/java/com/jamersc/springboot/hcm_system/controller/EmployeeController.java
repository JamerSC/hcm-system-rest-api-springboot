package com.jamersc.springboot.hcm_system.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamersc.springboot.hcm_system.dto.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.exception.EmployeeIDNotAllowedInRequestBodyException;
import com.jamersc.springboot.hcm_system.exception.EmployeeNotFoundException;
import com.jamersc.springboot.hcm_system.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employee = employeeService.getEmployees();

        if (employee.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        //return ResponseEntity.ok(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK); // HTTP 200 List of Employees
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
        Optional<EmployeeDTO> employee = employeeService.findById(id);

        return employee.map(ResponseEntity::ok) // HTTP 200 + body
                .orElseGet(()-> ResponseEntity.notFound().build()); // HTTP 404
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
//        // Employee theEmployee = employeeService.findById(id);
//        // return employeeService.findById(id);
//        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
//    }

    // Error validation handling
    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeDTO,
                                            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        Employee employee = employeeService.save(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED); // Created 201
    }

//    @PostMapping("/")
//    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
//        Employee employee = employeeService.save(employeeDTO);
//        return new ResponseEntity<>(employee, HttpStatus.CREATED); // Created 201
//    }

    @PutMapping("/")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeUpdateDTO employeeDTO,
                                            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        Employee employee = employeeService.update(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public EmployeeDTO patchEmployee(@PathVariable Long id,
                                                     @RequestBody Map<String, Object> patchPayload) {
        // find the employee
        Optional<EmployeeDTO> tempEmployee = employeeService.findById(id);

        if (tempEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee ID not found - " + id);
        }

        // throw the exception if the request body contains id - not allowed to change the key
        if (patchPayload.containsKey("id")) {
            throw new EmployeeIDNotAllowedInRequestBodyException("Cannot change primary key to a value that already exists - " + id);
        }

        // apply the patch payload to employee
        EmployeeDTO patchEmployee = apply(patchPayload);

        return employeeService.save(patchEmployee);
    }

    private EmployeeDTO apply(Map<String, Object> patchPayload, EmployeeDTO tempEmployee) {
        // convert employee object to JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class); // converted find object into JSON format

        // convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // merge patch updates into the employee node
        employeeNode.setAll(patchNode);

        // return - convert JSON object node back to Employee Object
        return objectMapper.convertValue(employeeNode, EmployeeDTO.class); // return inline
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Optional<EmployeeDTO> tempEmployee = employeeService.findById(id);

        if (tempEmployee.isEmpty()) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        employeeService.deleteEmployeeByID(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

/*    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        EmployeeDTO tempEmployee = employeeService.findById(id);
        employeeService.deleteEmployeeByID(id);
        return "Deleted employee id - " + id;
    }*/
}
