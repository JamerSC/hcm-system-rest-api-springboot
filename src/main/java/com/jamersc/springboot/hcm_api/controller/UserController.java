package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.user.UserCreateDto;
import com.jamersc.springboot.hcm_api.dto.user.UserDto;
import com.jamersc.springboot.hcm_api.dto.user.UserResponseDto;
import com.jamersc.springboot.hcm_api.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        Page<UserResponseDto> systemUsers = userService.getAllUsers(pageable);
        return new ResponseEntity<>(systemUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDto>> getUserById(@PathVariable Long id) {
        Optional<UserResponseDto> gotUser = userService.findUserById(id);
        return new ResponseEntity<>(gotUser, HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/create-user")
    public ResponseEntity<UserResponseDto> createEmployeeUserAccess(
            @PathVariable Long employeeId, @Valid @RequestBody UserCreateDto createDTO,
            Authentication authentication) {
        UserResponseDto createdUser = userService.createUser(employeeId, createDTO, authentication);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update-user")
    public ResponseEntity<UserResponseDto> updateEmployeeUser(
            @PathVariable Long id, @Valid @RequestBody UserDto userDTO,
            Authentication authentication) {
        UserResponseDto updatedUser = userService.update(userDTO, authentication);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

//    @PatchMapping("/{id}/")
//    public ResponseEntity<UserResponseDto> patchUser(
//            @PathVariable Long id, @Valid @RequestBody UserCreateDto createDTO) {
//        UserResponseDto userResponseDTO = null;
//        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
//    }

    // Todo soft delete
    @DeleteMapping("/{id}/delete-user")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User soft deleted, successfully!";
    }
}
