package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserResponseDTO;
import com.jamersc.springboot.hcm_system.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        Page<UserResponseDTO> users = userService.getAllUsers(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> getUserById(@PathVariable Long id) {
        Optional<UserResponseDTO> user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/create-user-access")
    public ResponseEntity<UserResponseDTO> createUserAccessForEmployee(
            @PathVariable Long employeeId, @Valid @RequestBody UserCreateDTO createDTO,
            Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.createUser(employeeId, createDTO, authentication);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id, @Valid @RequestBody UserDTO userDTO,
            Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.update(userDTO, authentication);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

//    @PatchMapping("/{id}/")
//    public ResponseEntity<UserResponseDTO> patchUser(
//            @PathVariable Long id, @Valid @RequestBody UserCreateDTO createDTO) {
//        UserResponseDTO userResponseDTO = null;
//        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
//    }

    // Todo soft delete
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User soft deleted, successfully!";
    }
}
