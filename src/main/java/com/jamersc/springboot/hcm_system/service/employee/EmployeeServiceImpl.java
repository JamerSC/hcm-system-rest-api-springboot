package com.jamersc.springboot.hcm_system.service.employee;

import com.jamersc.springboot.hcm_system.dto.employee.EmployeeCreateDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeResponseDTO;
import com.jamersc.springboot.hcm_system.dto.profile.EmployeeProfileDTO;
import com.jamersc.springboot.hcm_system.dto.employee.EmployeeUpdateDTO;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.exception.EmployeeNotFoundException;
import com.jamersc.springboot.hcm_system.entity.Employee;
import com.jamersc.springboot.hcm_system.mapper.EmployeeMapper;
import com.jamersc.springboot.hcm_system.repository.EmployeeRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Page<EmployeeResponseDTO> getAllEmployee(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper::entityToEmployeeResponseDTO);
    }

    @Override
    public Optional<EmployeeProfileDTO> findEmployeeProfileById(Long id) {
       return Optional.ofNullable(employeeRepository.findEmployeeWithUserAndRolesById(id)
                .map(employeeMapper::entityToProfileDto).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee id not found - " + id))
       );
    }

    public Optional<EmployeeResponseDTO> findEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .map(employeeMapper::entityToEmployeeResponseDTO).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee id not found - " + id))
        );
    }

    public Optional<EmployeeDTO> findById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .map(employeeMapper::entityToDto).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee id not found - " + id))
        );
    }

    @Override
    public EmployeeProfileDTO getEmployeeProfileByUsername(String username) {
        return employeeMapper.entityToProfileDto(
                employeeRepository.findEmployeeByUsername(username)
        );
    }

//    @Override
//    public Optional<EmployeeDTO> findById(Long id) {
//        return employeeRepository.findById(id)
//                .map(employeeMapper::entityToDto);
//    }

//    @Override
//    public EmployeeDTO findById(Long id) {
////        Employee employee = employeeRepository.findById(id).orElse(null);
////        if(employee != null) {
////            return employee;
////        }
////        throw new EmployeeIDNotAllowedException("Employee id not found. - " + id);
//        return employeeRepository.findById(id)
//                .map(employeeMapper::entityToDto)
//                .orElseThrow(() -> new EmployeeIDNotAllowedException("Employee id not found. - " + id));
//    }

    @Override
    public EmployeeResponseDTO save(EmployeeCreateDTO employeeDTO, Authentication authentication) {
        // get current user
        User currentUser = getUser(authentication);

        // map dto to entity
        Employee employee = employeeMapper.createDtoToEntity(employeeDTO);

        // set created & updated by with current user
        employee.setCreatedBy(currentUser);
        employee.setUpdatedBy(currentUser);

        // save the entity using the repository
        Employee saveEmployee = employeeRepository.save(employee);

        // map employee entity to response emp dto
        return employeeMapper.entityToEmployeeResponseDTO(saveEmployee);
    }

    @Override
    public Employee update(EmployeeUpdateDTO employeeDTO, Authentication authentication) {
        // get current user
        User currentUser = getUser(authentication);

        // Convert to entity
        Employee employee = employeeMapper.updateDtoToEntity(employeeDTO);

        // set updated by current user
        employee.setUpdatedBy(currentUser);

        // Update employee
        return employeeRepository.save(employee);
    }

    @Override
    public Employee patch(EmployeeDTO employeeDTO, Authentication authentication) {
        // get current user from authentication
        User currentUser = getUser(authentication);

        // map dto to entity
        Employee employee = employeeMapper.dtoToEntity(employeeDTO);

        // set updated by current user
        employee.setUpdatedBy(currentUser);

        // save/patch employee
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByID(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found - " + id));
        employeeRepository.deleteById(id);
    }

    // method for getting the current user - reusability
    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user is not found!"));
    }
}
