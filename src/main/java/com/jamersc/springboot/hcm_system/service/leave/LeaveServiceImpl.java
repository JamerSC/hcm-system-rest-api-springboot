package com.jamersc.springboot.hcm_system.service.leave;

import com.jamersc.springboot.hcm_system.dto.leave.LeaveCreateDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveResponseDTO;
import com.jamersc.springboot.hcm_system.dto.leave.LeaveUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.Leave;
import com.jamersc.springboot.hcm_system.entity.LeaveStatus;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.LeaveMapper;
import com.jamersc.springboot.hcm_system.repository.EmployeeRepository;
import com.jamersc.springboot.hcm_system.repository.LeaveRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    public LeaveServiceImpl(LeaveRepository leaveRepository, UserRepository userRepository, EmployeeRepository employeeRepository, LeaveMapper leaveMapper) {
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }


    @Override
    public List<LeaveResponseDTO> getAllLeaveRequest() {
        return leaveMapper.entitiesToResponseDtos(
                leaveRepository.findAll()
        );
    }

    @Override
    public Optional<LeaveResponseDTO> getLeaveRequestById(Long id) {
        return Optional.of(leaveRepository.findById(id)
                .map(leaveMapper::entityToResponseDto))
                .orElseThrow(()-> new RuntimeException("Leave request not found"));
    }

    @Override
    public LeaveResponseDTO submitLeaveRequest(LeaveCreateDTO dto, Authentication authentication) {
        User currentUser = getUser(authentication);

        Leave leaveRequest = new Leave();
        leaveRequest.setEmployee(currentUser.getEmployee());
        leaveRequest.setLeaveType(dto.getLeaveType());
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setReason(dto.getReason());
        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setSubmittedAt(new Date());
        leaveRequest.setCreatedBy(currentUser);
        leaveRequest.setUpdatedBy(currentUser);

        Leave submitted = leaveRepository.save(leaveRequest);

        return leaveMapper.entityToResponseDto(submitted);
    }

    @Override
    public LeaveResponseDTO updateLeaveRequest(Long id, LeaveUpdateDTO dto, Authentication authentication) {
        User currentUser = getUser(authentication);

        // 1. Find existing leave request
        Leave leaveRequest = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // 2. Ensure the logged-in user owns the leave request (optional security check)
        if (!leaveRequest.getEmployee().getId().equals(currentUser.getEmployee().getId())) {
            throw new RuntimeException("You are not allowed to update this leave request");
        }
        // 3. Update fields
        leaveRequest.setLeaveType(dto.getLeaveType());
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setReason(dto.getReason());
        leaveRequest.setUpdatedBy(currentUser);
        leaveRequest.setUpdatedAt(new Date());

        // 4. Save updated entity
        Leave updated = leaveRepository.save(leaveRequest);

        return leaveMapper.entityToResponseDto(updated);
    }

    @Override
    public LeaveResponseDTO approveLeaveRequest(Long id, Authentication authentication) {
        User currentUser = getUser(authentication);

        Leave requestedLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        requestedLeave.setStatus(LeaveStatus.APPROVED);
        requestedLeave.setApprovedBy(currentUser);
        requestedLeave.setUpdatedBy(currentUser);

        Leave approvedLeave = leaveRepository.save(requestedLeave);

        return leaveMapper.entityToResponseDto(approvedLeave);
    }

    @Override
    public LeaveResponseDTO rejectLeaveRequest(Long id, Authentication authentication) {
        User currentUser = getUser(authentication);

        Leave requestedLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        requestedLeave.setStatus(LeaveStatus.REJECTED);
        requestedLeave.setApprovedBy(currentUser);
        requestedLeave.setUpdatedBy(currentUser);

        Leave rejectedLeave = leaveRepository.save(requestedLeave);

        return leaveMapper.entityToResponseDto(rejectedLeave);
    }

    @Override
    public void deleteLeaveRequestById(Long id) {
        leaveRepository.deleteById(id);
    }

    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
