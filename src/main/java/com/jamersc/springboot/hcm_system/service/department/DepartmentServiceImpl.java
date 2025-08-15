package com.jamersc.springboot.hcm_system.service.department;

import com.jamersc.springboot.hcm_system.dto.department.DepartmentCreateDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentDTO;
import com.jamersc.springboot.hcm_system.dto.department.DepartmentResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Department;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.DepartmentMapper;
import com.jamersc.springboot.hcm_system.repository.DepartmentRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final UserRepository userRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<DepartmentResponseDTO> getAllDepartment() {
        return departmentMapper.entitiesToDeptResponseDto(
                departmentRepository.findAll()
        );
    }

    @Override
    public Optional<DepartmentResponseDTO> getDepartmentById(Long id) {
        return Optional.of(departmentRepository.findById(id)
                        .map(departmentMapper::entityToDepartmentResponseDto))
                .orElseThrow(() -> new RuntimeException("Department id found! " + id));
    }

    @Override
    public DepartmentResponseDTO save(DepartmentCreateDTO dto, Authentication authentication) {
        // get the current user from authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in the database"));

        // map the dto to entity
        Department department = departmentMapper.createDtoToEntity(dto);

        // Set created/updated by with the current
        department.setCreatedBy(currentUser);
        department.setUpdatedBy(currentUser);

        // save
        Department saveDepartment = departmentRepository.save(department);


        return departmentMapper.entityToDepartmentResponseDto(saveDepartment);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
