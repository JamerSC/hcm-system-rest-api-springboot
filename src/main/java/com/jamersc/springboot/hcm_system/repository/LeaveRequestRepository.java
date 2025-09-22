package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    //
}
