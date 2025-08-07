package com.jamersc.springboot.hcm_system.service.department;

import com.jamersc.springboot.hcm_system.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Optional<Department> getDepartmentById(Long id);
    Department save(Department department);
    void deleteDepartmentById(Long id);
}
