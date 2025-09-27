package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveUpdateDTO;
import com.jamersc.springboot.hcm_system.service.leave.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaveRequests() {
        List<LeaveResponseDTO> leaveRequests = leaveService.getAllLeaveRequest();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LeaveResponseDTO>> getLeaveRequestsById(@PathVariable Long id) {
        Optional<LeaveResponseDTO> leaveRequest = leaveService.getLeaveRequestById(id);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<LeaveResponseDTO> submitLeaveRequest(
            @Valid @RequestBody LeaveCreateDTO createDTO,
            Authentication authentication) {
        LeaveResponseDTO leaveRequest =
                leaveService.submitLeaveRequest(createDTO, authentication);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> updateLeaveRequest(
            @PathVariable Long id,
            @Valid @RequestBody LeaveUpdateDTO updateDTO,
            Authentication authentication) {
        LeaveResponseDTO updateLeaveRequest = leaveService.updateLeaveRequest(updateDTO, authentication);
        return new ResponseEntity<>(updateLeaveRequest, HttpStatus.OK);
    }

//    @PatchMapping("/")
//    public String approveLeaveRequest() {
//        return null;
//    }
//
//    @PatchMapping("/")
//    public String rejectLeaveRequest() {
//        return null;
//    }

    @GetMapping("/me")
    public String getMyLeaveRequest() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveResponseDTO> tempLeaveRequest = leaveService.getLeaveRequestById(id);

        if (tempLeaveRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
