package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
