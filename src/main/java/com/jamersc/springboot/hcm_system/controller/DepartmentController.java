package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.department.DepartmentCreateDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentResponseDTO;
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
    private ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<DepartmentResponseDTO>> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentResponseDTO> department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/")
    private ResponseEntity<DepartmentResponseDTO> createDepartment(
            @Valid @RequestBody DepartmentCreateDTO createDTO,
            Authentication authentication) {  // <-- Inject the Authentication object
        DepartmentResponseDTO department = departmentService.save(createDTO, authentication);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        Optional<DepartmentResponseDTO> tempDepartment =
                departmentService.getDepartmentById(id);

        if (tempDepartment.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
