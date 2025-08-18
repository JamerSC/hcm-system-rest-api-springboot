package com.jamersc.springboot.hcm_system.service.applicant;

import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.Application;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.exception.EmployeeNotFoundException;
import com.jamersc.springboot.hcm_system.mapper.ApplicantMapper;
import com.jamersc.springboot.hcm_system.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_system.repository.ApplicationRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository; // fetch applicant
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository; // fetch user repository
    private final ApplicantMapper applicantMapper; // mapping of applicant entity and dto

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicationRepository applicationRepository, UserRepository userRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.applicantMapper = applicantMapper;
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
    @Transactional // Essential if you're fetching and then saving/updating
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
    public Application applyForJob(String username, Long jobId) {
        return null;
    }


}
