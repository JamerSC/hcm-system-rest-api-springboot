package com.jamersc.springboot.hcm_system.controller;

import com.jamersc.springboot.hcm_system.service.leave.LeaveService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/")
    public String getAllLeaveRequests() {
        return null;
    }

    @PostMapping("/")
    public String submitLeaveRequest() {
        return null;
    }

    @PatchMapping("/")
    public String approveLeaveRequest() {
        return null;
    }

    @PatchMapping("/")
    public String rejectLeaveRequest() {
        return null;
    }

    @GetMapping("/me")
    public String getMyLeaveRequest() {
        return null;
    }
}
