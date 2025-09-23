package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public class LeaveServiceImpl implements LeaveService {

    @Override
    public List<LeaveRequestDTO> getAllLeaveRequest() {
        return null;
    }

    @Override
    public Optional<LeaveRequestDTO> getLeaveRequestById(Long id) {
        return Optional.empty();
    }

    @Override
    public LeaveRequestResponseDTO save(LeaveRequestCreateDTO dto, Authentication authentication) {
        return null;
    }

    @Override
    public LeaveRequestResponseDTO approveLeaveRequest(Long id, Authentication authentication) {
        return null;
    }

    @Override
    public LeaveRequestResponseDTO rejectLeaveRequest(Long id, Authentication authentication) {
        return null;
    }

    @Override
    public void deleteLeaveRequestById(Long id) {

    }
}
