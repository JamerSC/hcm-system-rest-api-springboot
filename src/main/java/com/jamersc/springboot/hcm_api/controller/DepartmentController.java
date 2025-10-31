package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.department.DepartmentCreateDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentPatchDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentResponseDto;
import com.jamersc.springboot.hcm_api.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    private ResponseEntity<Page<DepartmentResponseDto>> getAllDepartments(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {
        Page<DepartmentResponseDto> departments = departmentService.getAllDepartment(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartmentResponseDto>> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentResponseDto> department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentCreateDto createDTO,
            Authentication authentication) {  // <-- Inject the Authentication object
        DepartmentResponseDto department = departmentService.save(createDTO, authentication);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> patchDepartment(
            @PathVariable Long id, @RequestBody DepartmentPatchDto patchDTO, Authentication authentication) {
        DepartmentResponseDto patchedDepartment = departmentService.patchDepartment(id, patchDTO, authentication);
        return new ResponseEntity<>(patchedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        Optional<DepartmentResponseDto> tempDepartment =
                departmentService.getDepartmentById(id);

        if (tempDepartment.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
