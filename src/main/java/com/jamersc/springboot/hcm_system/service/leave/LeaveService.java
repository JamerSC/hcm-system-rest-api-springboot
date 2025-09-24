package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestUpdateDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface LeaveService {
    List<LeaveRequestResponseDTO> getAllLeaveRequest();
    Optional<LeaveRequestResponseDTO> getLeaveRequestById(Long id);
    LeaveRequestResponseDTO submitLeaveRequest(LeaveRequestCreateDTO dto, Authentication authentication);
    LeaveRequestResponseDTO updateLeaveRequest(LeaveRequestUpdateDTO dto, Authentication authentication);
    LeaveRequestResponseDTO approveLeaveRequest(Long id, Authentication authentication);
    LeaveRequestResponseDTO rejectLeaveRequest(Long id, Authentication authentication);
    void deleteLeaveRequestById(Long id);
}
