package com.jamersc.springboot.hcm_system.service.application;

import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.ApplicationStatus;
import com.jamersc.springboot.hcm_system.entity.JobStatus;
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
    public ApplicationResponseDTO reviewApplication(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.IN_REVIEW);
        application.setUpdatedBy(currentUser);

        // update application status
        Application applicationInReview = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(applicationInReview);
    }

    @Override
    public ApplicationResponseDTO scheduleInterview(Long id, Authentication authentication) {
        // find application id
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));

        // authenticate user & find by username
        User currentUser = getUser(authentication);

        // set the application status & updated by
        application.setStatus(ApplicationStatus.INTERVIEW_SCHEDULED);
        application.setUpdatedBy(currentUser);

        // update application status
        Application scheduledInterview = applicationRepository.save(application);
        return applicationMapper.entityToApplicationResponseDto(scheduledInterview);
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

        if (application.getStatus() == ApplicationStatus.SUBMITTED) {
            throw new RuntimeException("Application is newly submitted");
        }

        if (application.getStatus() == ApplicationStatus.IN_REVIEW) {
            throw new RuntimeException("Application in review");
        }

        if (application.getStatus() == ApplicationStatus.INTERVIEW_SCHEDULED) {
            throw new RuntimeException("Application is interview scheduled");
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
