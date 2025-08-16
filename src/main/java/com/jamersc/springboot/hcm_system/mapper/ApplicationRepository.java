package com.jamersc.springboot.hcm_system.mapper;

import com.jamersc.springboot.hcm_system.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    //
}
