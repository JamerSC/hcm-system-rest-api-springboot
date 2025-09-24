package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestUpdateDTO;
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
    public ResponseEntity<List<LeaveRequestResponseDTO>> getAllLeaveRequests() {
        List<LeaveRequestResponseDTO> leaveRequests = leaveService.getAllLeaveRequest();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LeaveRequestResponseDTO>> getLeaveRequestsById(@PathVariable Long id) {
        Optional<LeaveRequestResponseDTO> leaveRequest = leaveService.getLeaveRequestById(id);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<LeaveRequestResponseDTO> submitLeaveRequest(
            @Valid @RequestBody LeaveRequestCreateDTO createDTO,
            Authentication authentication) {
        LeaveRequestResponseDTO leaveRequest =
                leaveService.submitLeaveRequest(createDTO, authentication);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestResponseDTO> updateLeaveRequest(
            @Valid @RequestBody LeaveRequestUpdateDTO updateDTO, Authentication authentication) {
        LeaveRequestResponseDTO updateLeaveRequest = leaveService.updateLeaveRequest(updateDTO, authentication);
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
        Optional<LeaveRequestResponseDTO> tempLeaveRequest = leaveService.getLeaveRequestById(id);

        if (tempLeaveRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
