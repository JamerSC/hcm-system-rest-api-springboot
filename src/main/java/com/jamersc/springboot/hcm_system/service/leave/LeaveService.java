package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveUpdateDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface LeaveService {
    List<LeaveResponseDTO> getAllLeaveRequest();
    Optional<LeaveResponseDTO> getLeaveRequestById(Long id);
    LeaveResponseDTO submitLeaveRequest(LeaveCreateDTO dto, Authentication authentication);
    LeaveResponseDTO updateLeaveRequest(LeaveUpdateDTO dto, Authentication authentication);
    LeaveResponseDTO approveLeaveRequest(Long id, Authentication authentication);
    LeaveResponseDTO rejectLeaveRequest(Long id, Authentication authentication);
    void deleteLeaveRequestById(Long id);
}
