package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.dto.application.ApplicationResponseDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.Job;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.ApplicantMapper;
import com.jamersc.springboot.hcm_system.mapper.ApplicationMapper;
import com.jamersc.springboot.hcm_system.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_system.repository.ApplicationRepository;
import com.jamersc.springboot.hcm_system.repository.JobRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository; // fetch applicant
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository; // fetch user repository
    private final ApplicantMapper applicantMapper; // mapping of applicant entity and dto
    private final ApplicationMapper applicationMapper;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository, ApplicantMapper applicantMapper, ApplicationMapper applicationMapper) {
        this.applicantRepository = applicantRepository;
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicantMapper = applicantMapper;
        this.applicationMapper = applicationMapper;
    }


    @Override
    public List<ApplicantDto> getAllApplicant() {
        return applicantMapper.entityToApplicantDtoList(applicantRepository.findAll());
    }

    @Override
    public Optional<ApplicantDto> getApplicantById(Long id) {

        return Optional.ofNullable(applicantRepository.findById(id)
                .map(applicantMapper::entityToApplicantDto)
                .orElseThrow(()-> new RuntimeException("Applicant id not found - " + id)));
    }

    @Override
    public ApplicantProfileDTO getApplicantProfile(String username) {
        return applicantMapper.entityToProfileDto(
                applicantRepository.findApplicantByUsername(username)
        );
    }

    @Override
//    @Transactional // Essential if you're fetching and then saving/updating
    public void updateApplicantProfile(String username,
                                       ApplicantProfileDTO profileDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Authenticated user not found: " + username));

        Applicant applicant = applicantRepository.findByApplicantUser(user)
                .orElseGet(() -> { // Create new Applicant if not found (e.g., first profile update after registration)
                    Applicant newApplicant = new Applicant();
                    newApplicant.setUser(user);
                    //set default status
                    newApplicant.setCurrentStatus("Profile Created");
                    return newApplicant;
                });
        // Map DTO fields to entity. You can use a mapper library (MapStruct) here.
        applicant.setFirstName(profileDTO.getFirstName());
        applicant.setLastName(profileDTO.getLastName());

        applicantRepository.save(applicant);
    }

    @Override
    public void saveResume(String username, String file) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Applicant profile not found for user: " + username));
        Applicant applicant = applicantRepository.findByApplicantUser(user)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found for user: " + username));

        applicant.setCvFilePath(file);
        applicantRepository.save(applicant);
    }

    @Override
    public void applyForJob(Long id, Authentication authentication) {
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

        applicationRepository.save(newApplication);
    }

    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplicantJobsApplied(Authentication authentication) {
        User applicantUser = getUser(authentication);
        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found."));

        return applicationMapper.entitiesToResponseDtos(applicationRepository.findApplicantApplicationsById(applicant.getId()));
    }

    @Override
    public Optional<ApplicationResponseDTO> getApplicantJobsAppliedById(Long id, Authentication authentication) {
        // find the application
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // fetch applicant entity using user
        User applicantUser = getUser(authentication);
        Applicant applicant = applicantRepository.findByApplicantUser(applicantUser)
                .orElseThrow(()-> new RuntimeException("Applicant profile not found."));

        return Optional.ofNullable(
                applicationRepository.findById(application.getId())
                        .map(applicationMapper::entityToApplicationResponseDto)
                        .orElseThrow(()-> new RuntimeException("Application id not found")));
    }
}
