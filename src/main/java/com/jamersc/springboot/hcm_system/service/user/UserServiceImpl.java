package com.jamersc.springboot.hcm_system.service.user;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeResponseDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserResponseDTO;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserResponseDTO> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserResponseDTO save(UserCreateDTO createDTO, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
