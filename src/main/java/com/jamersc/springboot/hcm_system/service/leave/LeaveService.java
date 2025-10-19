package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface LeaveService {
    Page<LeaveResponseDTO> getAllLeaveRequest(Pageable pageable);
    Optional<LeaveResponseDTO> getLeaveRequestById(Long id);
    Page<LeaveResponseDTO> getMyLeaveRequests(Pageable pageable, Authentication authentication);
    LeaveResponseDTO submitLeaveRequest(LeaveCreateDTO dto, Authentication authentication);
    LeaveResponseDTO cancelLeaveRequest(Long id, Authentication authentication);
    LeaveResponseDTO updateLeaveRequest(Long id, LeaveUpdateDTO dto, Authentication authentication);
    LeaveResponseDTO approveLeaveRequest(Long id, Authentication authentication);
    LeaveResponseDTO rejectLeaveRequest(Long id, Authentication authentication);
    void deleteLeaveRequestById(Long id);
}
