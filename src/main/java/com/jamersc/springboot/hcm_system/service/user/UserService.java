package com.jamersc.springboot.hcm_system.service.user;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeResponseDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> findUserById(Long id);
    UserResponseDTO save(UserCreateDTO createDTO, Authentication authentication);
    void deleteUserById(Long id);
}
