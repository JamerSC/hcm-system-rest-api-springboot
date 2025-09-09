package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserDTO;
import com.jamersc.springboot.hcm_system.dto.user.UserResponseDTO;
import com.jamersc.springboot.hcm_system.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTO = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/")
    public ResponseEntity<UserResponseDTO> createUser(
            @PathVariable Long id, @Valid @RequestBody UserCreateDTO createDTO,
            Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.save(createDTO, authentication);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id, @Valid @RequestBody UserCreateDTO createDTO,
            Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.save(createDTO, authentication);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
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
