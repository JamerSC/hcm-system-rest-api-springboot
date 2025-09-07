package com.jamersc.springboot.hcm_system.service.application;

import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.ApplicationStatus;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.ApplicationMapper;
import com.jamersc.springboot.hcm_system.repository.ApplicationRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final UserRepository userRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplication() {
        return applicationMapper.entitiesToResponseDtos(
                applicationRepository.findAll()
        );
    }

    @Override
    public Optional<ApplicationResponseDTO> getApplicationById(Long id) {
        return Optional.ofNullable(applicationRepository.findById(id)
                .map(applicationMapper::entityToApplicationResponseDto)
                .orElseThrow(()-> new RuntimeException("Application not found")));
    }

    @Override
    public ApplicationResponseDTO initialQualification(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.INITIAL_QUALIFICATION);
        application.setUpdatedBy(currentUser);

        // update application status
        Application initialQualification = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(initialQualification);
    }

    @Override
    public ApplicationResponseDTO firstInterview(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.FIRST_INTERVIEW);
        application.setUpdatedBy(currentUser);

        // update application status
        Application firstInterview = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(firstInterview);
    }

    @Override
    public ApplicationResponseDTO secondInterview(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.SECOND_INTERVIEW);
        application.setUpdatedBy(currentUser);

        // update application status
        Application secondInterview = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(secondInterview);
    }

    @Override
    public ApplicationResponseDTO contractProposal(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.CONTRACT_PROPOSAL);
        application.setUpdatedBy(currentUser);

        // update application status
        Application contractProposal = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(contractProposal);
    }

    @Override
    public ApplicationResponseDTO contractSigned(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.CONTRACT_SIGNED);
        application.setUpdatedBy(currentUser);

        // update application status
        Application contractSigned = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(contractSigned);
    }

    @Override
    public ApplicationResponseDTO approveApplication(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.APPROVED);
        application.setUpdatedBy(currentUser);

        // update application status
        Application approvedApplication = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(approvedApplication);
    }

    @Override
    public ApplicationResponseDTO rejectApplication(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.REJECTED);
        application.setUpdatedBy(currentUser);

        // update application status
        Application rejectedApplication = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(rejectedApplication);
    }

    @Override
    public ApplicationResponseDTO hireApplication(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        if (application.getStatus() == ApplicationStatus.NEW) {
            throw new RuntimeException("This is a new application");
        }

        if (application.getStatus() == ApplicationStatus.INITIAL_QUALIFICATION) {
            throw new RuntimeException("Application is initial qualification");
        }

        if (application.getStatus() == ApplicationStatus.FIRST_INTERVIEW) {
            throw new RuntimeException("Application first interview");
        }

        if (application.getStatus() == ApplicationStatus.REJECTED) {
            throw new RuntimeException("Application is rejected");
        }

        // set the application status & updated by
        application.setStatus(ApplicationStatus.HIRED);
        application.setUpdatedBy(currentUser);

        // update application status
        Application hireApplicant = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(hireApplicant);
    }

    // Authenticate the user - extracted method
    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in the database!"));
    }
}
