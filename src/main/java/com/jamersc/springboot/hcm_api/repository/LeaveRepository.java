package com.jamersc.springboot.hcm_api.repository;

import com.jamersc.springboot.hcm_api.entity.Employee;
import com.jamersc.springboot.hcm_api.entity.Leave;
import com.jamersc.springboot.hcm_api.entity.LeaveType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    //
    Page<Leave> findByEmployee(Pageable pageable, Employee employee);

    Long countByLeaveType(LeaveType leaveType);
}
