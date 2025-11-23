package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.department.DepartmentCreateDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentPatchDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentResponseDto;
import com.jamersc.springboot.hcm_api.service.department.DepartmentService;
import com.jamersc.springboot.hcm_api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    private ResponseEntity<ApiResponse<Page<DepartmentResponseDto>>> getAllDepartments(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {
        Page<DepartmentResponseDto> retrievedDepartments = departmentService.getAllDepartments(pageable);
        ApiResponse<Page<DepartmentResponseDto>> response = ApiResponse.<Page<DepartmentResponseDto>>builder()
                .success(true)
                .message("List of departments retrieved successfully!")
                .data(retrievedDepartments)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<DepartmentResponseDto>>> getDepartment(@PathVariable Long id) {
        Optional<DepartmentResponseDto> retrievedDepartment = departmentService.getDepartment(id);
        ApiResponse<Optional<DepartmentResponseDto>> response = ApiResponse.<Optional<DepartmentResponseDto>>builder()
                .success(true)
                .message("Department retrieved successfully!")
                .data(retrievedDepartment)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(
            @Valid @RequestBody DepartmentCreateDto createDto,
            Authentication authentication) {
        DepartmentResponseDto createdDepartment = departmentService.createDepartment(createDto, authentication);
        ApiResponse<DepartmentResponseDto> response = ApiResponse.<DepartmentResponseDto>builder()
                .success(true)
                .message("Department created successfully!")
                .data(createdDepartment)
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> updateDepartment(
            @PathVariable Long id, @RequestBody DepartmentPatchDto patchDTO, Authentication authentication) {
        DepartmentResponseDto updatedDepartment = departmentService.updateDepartment(id, patchDTO, authentication);
        ApiResponse<DepartmentResponseDto> response = ApiResponse.<DepartmentResponseDto>builder()
                .success(true)
                .message("Department updated successfully!")
                .data(updatedDepartment)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDepartment(@PathVariable Long id) {
        Optional<DepartmentResponseDto> department =
                departmentService.getDepartment(id);

        if (department.isEmpty()) {
            ApiResponse<String> response = ApiResponse.<String>builder()
                    .success(true)
                    .message("Department not found!")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(response);
        }

        departmentService.deleteDepartment(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Department deleted successfully!")
                .data(null)
                .status(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
