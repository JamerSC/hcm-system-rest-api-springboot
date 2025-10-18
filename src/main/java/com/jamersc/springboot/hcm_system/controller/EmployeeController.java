package com.jamersc.springboot.hcm_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamersc.springboot.hcm_system.dto.employee.*;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.exception.EmployeeIDNotAllowedInRequestBodyException;
import com.jamersc.springboot.hcm_system.exception.EmployeeNotFoundException;
import com.jamersc.springboot.hcm_system.service.employee.EmployeeService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper, Validator validator) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }


    // Get all employees
    @GetMapping("/")
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<EmployeeResponseDTO> employees = employeeService.getAllEmployee(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK); // HTTP 200 List of Employees
    }

    // Get employee profile with username & role
    @GetMapping("/{id}/profile")
    public ResponseEntity<Optional<EmployeeProfileDTO>> getEmployeeProfile(@PathVariable Long id) {
        Optional<EmployeeProfileDTO> profile = employeeService.findEmployeeProfileById(id);
        return ResponseEntity.ok(profile);
    }

    // Get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeResponseDTO> employee = employeeService.findEmployeeById(id);

        return employee.map(ResponseEntity::ok) // HTTP 200 + body
                .orElseGet(()-> ResponseEntity.notFound().build()); // HTTP 404
    }

    @GetMapping("/me/profile")
    public ResponseEntity<EmployeeProfileDTO> getMyEmployeeProfile(Authentication authentication) {
        EmployeeProfileDTO myProfile = employeeService.getMyEmployeeProfile(authentication);
        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    // Create Employee
    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO employeeDTO,
            Authentication authentication) {

        EmployeeResponseDTO employee = employeeService.save(employeeDTO, authentication);

        return new ResponseEntity<>(employee, HttpStatus.CREATED); // Created 201
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> patchEmployeeProfile(
            @PathVariable Long id, @RequestBody EmployeePatchDTO dto, Authentication authentication) {
        EmployeeResponseDTO patchedEmployee = employeeService.patchEmployee(id, dto, authentication);
        return new ResponseEntity<>(patchedEmployee, HttpStatus.OK);
    }

    private EmployeeDTO apply(Map<String, Object> patchPayload, EmployeeDTO tempEmployee) {
        // convert employee object to JSON object node
        // add Object mapper in dependency injection
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // merge patch updates into the employee node
        employeeNode.setAll(patchNode);

        // return - convert JSON object node back to Employee Object
        return objectMapper.convertValue(employeeNode, EmployeeDTO.class);
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
}
