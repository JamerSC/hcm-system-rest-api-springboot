package com.jamersc.springboot.hcm_api.service.applicant;

import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantProfileDto;
import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantResponseDto;
import com.jamersc.springboot.hcm_api.dto.application.ApplicationResponseDto;
import com.jamersc.springboot.hcm_api.entity.*;
import com.jamersc.springboot.hcm_api.mapper.ApplicantMapper;
import com.jamersc.springboot.hcm_api.mapper.ApplicationMapper;
import com.jamersc.springboot.hcm_api.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_api.repository.ApplicationRepository;
import com.jamersc.springboot.hcm_api.repository.JobRepository;
import com.jamersc.springboot.hcm_api.repository.UserRepository;
import com.jamersc.springboot.hcm_api.service.email.EmailService;
import com.jamersc.springboot.hcm_api.service.file.FileStorageService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

    private static final Logger log = LoggerFactory.getLogger(ApplicantServiceImpl.class);
    private final ApplicantRepository applicantRepository; // fetch applicant
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository; // fetch user repository
    private final ApplicantMapper applicantMapper; // mapping of applicant entity and dto
    private final ApplicationMapper applicationMapper;
    private final FileStorageService fileStorageService;
    private final EmailService emailService;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository, ApplicantMapper applicantMapper, ApplicationMapper applicationMapper, FileStorageService fileStorageService, EmailService emailService) {
        this.applicantRepository = applicantRepository;
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicantMapper = applicantMapper;
        this.applicationMapper = applicationMapper;
        this.fileStorageService = fileStorageService;
        this.emailService = emailService;
    }

    @Override
    public Page<ApplicantResponseDto> getAllApplicant(Pageable pageable) {
        // fetch applicant from repository
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        // map the Page<Applicant> to Page<JobDTO>
        return applicants.map(applicantMapper::entityToResponseDto);
    }

    @Override
    public Optional<ApplicantResponseDto> getApplicantById(Long id) {

        return Optional.ofNullable(applicantRepository.findById(id)
                .map(applicantMapper::entityToResponseDto)
                .orElseThrow(()-> new RuntimeException("Applicant id not found - " + id)));
    }

    @Override
    public ApplicantResponseDto getApplicantProfile(String username) {
        return applicantMapper.entityToResponseDto(
                applicantRepository.findApplicantByUsername(username)
        );
    }

    @Override
    public ApplicantResponseDto updateApplicantProfile(String username,
                                                       ApplicantProfileDto profileDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Authenticated user not found: " + username));

        Applicant applicant = applicantRepository.findByApplicantUser(user)
                .orElseGet(() -> { // Create new Applicant if not found (e.g., first profile update after registration)
                    Applicant newApplicant = new Applicant();
                    newApplicant.setUser(user);
                    return newApplicant;
                });
        // Map DTO fields to entity. You can use a mapper library (MapStruct) here.
        applicant.setFirstName(profileDTO.getFirstName());
        applicant.setLastName(profileDTO.getLastName());
        applicant.setPhoneNumber(profileDTO.getPhoneNumber());
        applicant.setAddress(profileDTO.getAddress());
        applicant.setEducationLevel(profileDTO.getEducationLevel());

        Applicant updatedApplicant = applicantRepository.save(applicant);

        return applicantMapper.entityToResponseDto(updatedApplicant);
    }

    @Override
    public ApplicantResponseDto uploadResume(MultipartFile file, Authentication authentication) {
        User applicantUser = getUser(authentication);
        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found for user: " + applicantUser));
        // 1. BEST PRACTICE: Store the file first, retrieve the path/name
        // Use the user's ID to make the file name unique and searchable
        String storeFileName = fileStorageService.storeFile(file, String.valueOf(applicantUser.getId()));

        // 2. Update the database record with the file path
        applicant.setCvFilePath(storeFileName);
        applicant.setUpdatedBy(applicantUser);

        Applicant uploadResume = applicantRepository.save(applicant);

        return applicantMapper.entityToResponseDto(uploadResume);
    }

    @Override
    public ApplicationResponseDto applyForJob(Long id, Authentication authentication) {
        // find the job
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // fetch applicant entity using user entity
        User applicantUser = getUser(authentication);

        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found!"));

        // check applicant if applied
        if (applicationRepository.findByApplicantAndJob(applicant, job).isPresent()) {
            throw new RuntimeException("You have already applied for this job.");
        }

        // create and save new application
        Application newApplication = new Application();
        newApplication.setApplicant(applicant);
        newApplication.setJob(job);
        newApplication.setUpdatedBy(applicantUser);

        Application submittedApplication = applicationRepository.save(newApplication);

        // email notification
        String email = submittedApplication.getApplicant().getUser().getEmail();
        String fullName = submittedApplication.getApplicant().getApplicantFullName();
        String jobTitle = submittedApplication.getJob().getTitle();
        long applicationId = submittedApplication.getId();

        emailService.sendSubmittedApplicationEmail(email, fullName, applicationId, jobTitle);

        return applicationMapper.entityToApplicationResponseDto(submittedApplication);
    }

    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));
    }

    @Override
    public Page<ApplicationResponseDto> getAllApplicantJobsApplied(Pageable pageable, Authentication authentication) {
        User applicantUser = getUser(authentication);
        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found."));
        Page<Application> applications = applicationRepository.findApplicantApplicationsById(pageable ,applicant.getId());
        return applications.map(applicationMapper::entityToApplicationResponseDto);
    }

    @Override
    public Optional<ApplicationResponseDto> getApplicantJobsAppliedById(Long id, Authentication authentication) {
        // find the application
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // fetch applicant entity using user
        User applicantUser = getUser(authentication);
        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(()-> new RuntimeException("Applicant profile not found."));

        return Optional.ofNullable(
                applicationRepository.findByApplicantAndId(applicant, application.getId())
                        .map(applicationMapper::entityToApplicationResponseDto)
                        .orElseThrow(()-> new RuntimeException("Application id not found")));
    }

    @Override
    public ApplicationResponseDto withdrawApplication(Long id, Authentication authentication) {
        // find application by id
        Application application = applicationRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Application id not found!"));

        // fetch applicant
        User applicantUser = getUser(authentication);

        application.setStatus(ApplicationStatus.WITHDRAWN);
        application.setUpdatedBy(applicantUser);
        Application withdrawnApplication = applicationRepository.save(application);

        return applicationMapper.entityToApplicationResponseDto(withdrawnApplication);
    }

    @Override
    public void deleteApplicantAccount(Authentication authentication) {
//      1. Get the authenticated user's details
        User userDetails = getUser(authentication);

//      2. Find the User entity to be deleted
        User userToDelete = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("Authenticated user not found."));

//      3. Find the Applicant profile
        Applicant applicantProfile = applicantRepository.findByApplicantUser(userToDelete)
                .orElseThrow(()-> new RuntimeException("Applicant profile not found"));

//      4. Handle child entities (e.g., Applications) before deleting the parent
//      A user's Applications must be deleted first to prevent foreign key constraint issues.
        List<Application> applications = applicationRepository.findByApplicant(applicantProfile);
        applicationRepository.deleteAll(applications);

        userRepository.delete(userToDelete);
    }
}
