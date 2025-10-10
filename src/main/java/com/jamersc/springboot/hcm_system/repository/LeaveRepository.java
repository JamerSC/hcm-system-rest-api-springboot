package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.entity.Leave;
import com.jamersc.springboot.hcm_system.entity.LeaveType;
import com.jamersc.springboot.hcm_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    //
    Page<Leave> findByEmployee(Pageable pageable, Employee employee);

    Long countByLeaveType(LeaveType leaveType);
}
