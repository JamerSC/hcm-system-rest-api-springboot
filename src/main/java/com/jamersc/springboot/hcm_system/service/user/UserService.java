package com.jamersc.springboot.hcm_system.service.user;

import com.jamersc.springboot.hcm_system.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    Optional<UserResponseDTO> findUserById(Long id);
    UserResponseDTO createUser(Long employeeId, UserCreateDTO createDTO, Authentication authentication);
    UserResponseDTO update(UserDTO userDTO, Authentication authentication);
    void deleteUserById(Long id);
}
