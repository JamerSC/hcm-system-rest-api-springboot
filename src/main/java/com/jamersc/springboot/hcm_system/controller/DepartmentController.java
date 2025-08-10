package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.department.DepartmentCreateDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentDTO;
import com.jamersc.springboot.hcm_system.entity.Department;
import com.jamersc.springboot.hcm_system.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    private ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<DepartmentDTO>> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/")
    private ResponseEntity<Department> createDepartment(
            @Valid @RequestBody DepartmentCreateDTO createDTO,
            Authentication authentication) {  // <-- Inject the Authentication object
        Department department = departmentService.save(createDTO, authentication);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> tempDepartment = departmentService.getDepartmentById(id);

        if (tempDepartment.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
