package com.jamersc.springboot.hcm_api.controller;

import com.jamersc.springboot.hcm_api.dto.dashboard.DashboardDto;
import com.jamersc.springboot.hcm_api.service.dashboard.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public ResponseEntity<DashboardDto> getDashboardData() {
        DashboardDto dashboardData = dashboardService.getDashboardData();
        return new ResponseEntity<>(dashboardData, HttpStatus.OK);
    }
}
