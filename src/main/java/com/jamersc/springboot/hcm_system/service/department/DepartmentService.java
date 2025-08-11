package com.jamersc.springboot.hcm_system.service.department;

import com.jamersc.springboot.hcm_system.dto.department.DepartmentCreateDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Department;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartment();
    Optional<DepartmentDTO> getDepartmentById(Long id);
    DepartmentResponseDTO save(DepartmentCreateDTO dto, Authentication authentication);
    void deleteDepartmentById(Long id);
}
