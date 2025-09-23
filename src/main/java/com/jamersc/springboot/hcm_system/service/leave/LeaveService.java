package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface LeaveService {
    List<LeaveRequestDTO> getAllLeaveRequest();
    Optional<LeaveRequestDTO> getLeaveRequestById(Long id);
    LeaveRequestResponseDTO save(LeaveRequestCreateDTO dto, Authentication authentication);
    LeaveRequestResponseDTO approveLeaveRequest(Long id, Authentication authentication);
    LeaveRequestResponseDTO rejectLeaveRequest(Long id, Authentication authentication);
    void deleteLeaveRequestById(Long id);
}
