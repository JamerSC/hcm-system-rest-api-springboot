package com.jamersc.springboot.hcm_api.repository;

import com.jamersc.springboot.hcm_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
