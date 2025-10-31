package com.jamersc.springboot.hcm_api.service.user;

import com.jamersc.springboot.hcm_api.dto.user.UserCreateDto;
import com.jamersc.springboot.hcm_api.dto.user.UserDto;
import com.jamersc.springboot.hcm_api.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {

    Page<UserResponseDto> getAllUsers(Pageable pageable);
    Optional<UserResponseDto> findUserById(Long id);
    UserResponseDto createUser(Long employeeId, UserCreateDto createDTO, Authentication authentication);
    UserResponseDto update(UserDto userDTO, Authentication authentication);
    void deleteUserById(Long id);
}
