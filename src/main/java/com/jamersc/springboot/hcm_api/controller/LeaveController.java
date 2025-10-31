package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.leave.LeaveCreateDto;
import com.jamersc.springboot.hcm_api.dto.leave.LeaveResponseDto;
import com.jamersc.springboot.hcm_api.dto.leave.LeaveUpdateDto;
import com.jamersc.springboot.hcm_api.service.leave.LeaveService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<LeaveResponseDto>> getAllLeaveRequests(
            @PageableDefault(page = 0, size = 10, sort="submittedAt") Pageable pageable) {
        Page<LeaveResponseDto> leaveRequests = leaveService.getAllLeaveRequest(pageable);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LeaveResponseDto>> getLeaveRequestsById(@PathVariable Long id) {
        Optional<LeaveResponseDto> leaveRequest = leaveService.getLeaveRequestById(id);
        return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
    }


    @PostMapping("/submit")
    public ResponseEntity<LeaveResponseDto> submitLeaveRequest(
            @Valid @RequestBody LeaveCreateDto createDTO,
            Authentication authentication) {
        LeaveResponseDto submittedLeaveRequest =
                leaveService.submitLeaveRequest(createDTO, authentication);
        return new ResponseEntity<>(submittedLeaveRequest, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<LeaveResponseDto> cancelLeaveRequest(
            @PathVariable Long id, Authentication authentication) {
        LeaveResponseDto canceledLeaveRequest = leaveService.cancelLeaveRequest(id, authentication);
        return new ResponseEntity<>(canceledLeaveRequest, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Page<LeaveResponseDto>> getMyLeaveRequests(
            @PageableDefault(page = 0, size = 10, sort="submittedAt") Pageable pageable, Authentication authentication) {
        Page<LeaveResponseDto> requestedLeaves = leaveService.getMyLeaveRequests(pageable, authentication);
        return new ResponseEntity<>(requestedLeaves, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponseDto> updateLeaveRequest(
            @PathVariable Long id,
            @Valid @RequestBody LeaveUpdateDto updateDTO,
            Authentication authentication) {
        LeaveResponseDto updateLeaveRequest = leaveService.updateLeaveRequest(id, updateDTO, authentication);
        return new ResponseEntity<>(updateLeaveRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/approve-leave")
    public ResponseEntity<LeaveResponseDto> approveLeaveRequest(
            @PathVariable Long id, Authentication authentication) {
        LeaveResponseDto approvedLeaveRequest = leaveService.approveLeaveRequest(id, authentication);
        return new ResponseEntity<>(approvedLeaveRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/reject-leave")
    public ResponseEntity<LeaveResponseDto> rejectLeaveRequest(
            @PathVariable Long id, Authentication authentication) {
        LeaveResponseDto rejectedLeaveRequest = leaveService.rejectLeaveRequest(id, authentication);
        return new ResponseEntity<>(rejectedLeaveRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveResponseDto> tempLeaveRequest = leaveService.getLeaveRequestById(id);

        if (tempLeaveRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        leaveService.deleteLeaveRequestById(id);

        return ResponseEntity.noContent().build();
    }
}
