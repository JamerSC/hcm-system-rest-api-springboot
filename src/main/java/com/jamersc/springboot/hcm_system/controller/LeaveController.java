package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Leave;
import com.jamersc.springboot.hcm_system.service.leave.LeaveService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<LeaveResponseDTO>> getAllLeaveRequests(
            @PageableDefault(page = 0, size = 10, sort="submittedAt") Pageable pageable) {
        Page<LeaveResponseDTO> leaveRequests = leaveService.getAllLeaveRequest(pageable);
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

    @GetMapping("/me/profile")
    public ResponseEntity<Page<LeaveResponseDTO>> getMyLeaveRequests(
            @PageableDefault(page = 0, size = 10, sort="submittedAt") Pageable pageable, Authentication authentication) {
        Page<LeaveResponseDTO> requestedLeaves = leaveService.getMyLeaveRequests(pageable, authentication);
        return new ResponseEntity<>(requestedLeaves, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> updateLeaveRequest(
            @PathVariable Long id,
            @Valid @RequestBody LeaveUpdateDTO updateDTO,
            Authentication authentication) {
        LeaveResponseDTO updateLeaveRequest = leaveService.updateLeaveRequest(id, updateDTO, authentication);
        return new ResponseEntity<>(updateLeaveRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/approve-leave")
    public ResponseEntity<LeaveResponseDTO> approveLeaveRequest(
            @PathVariable Long id, Authentication authentication) {
        LeaveResponseDTO approvedLeaveRequest = leaveService.approveLeaveRequest(id, authentication);
        return new ResponseEntity<>(approvedLeaveRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/reject-leave")
    public ResponseEntity<LeaveResponseDTO> rejectLeaveRequest(
            @PathVariable Long id, Authentication authentication) {
        LeaveResponseDTO rejectedLeaveRequest = leaveService.rejectLeaveRequest(id, authentication);
        return new ResponseEntity<>(rejectedLeaveRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveResponseDTO> tempLeaveRequest = leaveService.getLeaveRequestById(id);

        if (tempLeaveRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        leaveService.deleteLeaveRequestById(id);

        return ResponseEntity.noContent().build();
    }
}
