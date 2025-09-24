package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveRequestUpdateDTO;
import com.jamersc.springboot.hcm_system.mapper.LeaveMapper;
import com.jamersc.springboot.hcm_system.repository.EmployeeRepository;
import com.jamersc.springboot.hcm_system.repository.LeaveRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    public LeaveServiceImpl(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository, LeaveMapper leaveMapper) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }


    @Override
    public List<LeaveRequestResponseDTO> getAllLeaveRequest() {
        return leaveMapper.entitiesToResponseDtos(
                leaveRequestRepository.findAll()
        );
    }

    @Override
    public Optional<LeaveRequestResponseDTO> getLeaveRequestById(Long id) {
        return Optional.of(leaveRequestRepository.findById(id)
                .map(leaveMapper::entityToResponseDto))
                .orElseThrow(()-> new RuntimeException("Leave request not found"));
    }

    @Override
    public LeaveRequestResponseDTO submitLeaveRequest(LeaveRequestCreateDTO dto, Authentication authentication) {
        return null;
    }

    @Override
    public LeaveRequestResponseDTO updateLeaveRequest(LeaveRequestUpdateDTO dto, Authentication authentication) {
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
