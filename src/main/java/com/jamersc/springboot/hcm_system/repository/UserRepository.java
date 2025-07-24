package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
