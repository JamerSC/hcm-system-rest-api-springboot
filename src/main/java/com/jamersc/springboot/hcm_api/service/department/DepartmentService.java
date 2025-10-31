package com.jamersc.springboot.hcm_api.service.department;

import com.jamersc.springboot.hcm_api.dto.department.DepartmentCreateDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentPatchDto;
import com.jamersc.springboot.hcm_api.dto.department.DepartmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface DepartmentService {
    Page<DepartmentResponseDto> getAllDepartment(Pageable pageable);
    Optional<DepartmentResponseDto> getDepartmentById(Long id);
    DepartmentResponseDto save(DepartmentCreateDto dto, Authentication authentication);
    DepartmentResponseDto patchDepartment(Long id, DepartmentPatchDto dto, Authentication authentication);
    void deleteDepartmentById(Long id);
}
