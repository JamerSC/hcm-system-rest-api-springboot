package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //
}
