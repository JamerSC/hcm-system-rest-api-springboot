package com.jamersc.springboot.hcm_system.service;

import com.jamersc.springboot.hcm_system.dto.ApplicantDto;
import com.jamersc.springboot.hcm_system.dto.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.User;
import com.jamersc.springboot.hcm_system.mapper.ApplicantMapper;
import com.jamersc.springboot.hcm_system.repository.ApplicantRepository;
import com.jamersc.springboot.hcm_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    final private ApplicantRepository applicantRepository; // fetch applicant
    final private UserRepository userRepository; // fetch user repository
    final private ApplicantMapper applicantMapper; // mapping of applicant entity and dto

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, UserRepository userRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.userRepository = userRepository;
        this.applicantMapper = applicantMapper;
    }


    @Override
    public List<ApplicantDto> getAllApplicant() {
        return null;
    }

    @Override
    public ApplicantDto getApplicantById(Long id) {
        return null;
    }

    @Override
    @Transactional // Essential if you're fetching and then saving/updating
    public void updateProfile(String username,
                              ApplicantProfileDTO profileDTO) {
//        User user = userRepository.findUsername(username)
//                .orElseThrow(()-> new RuntimeException("Authenticated user not found: " + username));
//
//        Applicant applicant = applicantRepository.findByUser(user)
//                .orElseGet(() -> { // Create new Applicant if not found (e.g., first profile update after registration)
//                    Applicant newApplicant = new Applicant();
//                    newApplicant.setUser(user);
//                    //set default status
//                    newApplicant.setCurrentStatus("Profile Created");
//                    return newApplicant;
//                });
//        // Map DTO fields to entity. You can use a mapper library (MapStruct) here.
//        applicant.setFirstName(profileDTO.getFirstName());
//        applicant.setLastName(profileDTO.getLastName());
//
//        applicantRepository.save(applicant);
    }

    @Override
    public void saveResume(String username, String file) {
//        User user = userRepository.findUsername(username)
//                .orElseThrow(()-> new RuntimeException("Applicant profile not found for user: " + username));
//        Applicant applicant = applicantRepository.findByUser(user)
//                .orElseThrow(() -> new RuntimeException("Applicant profile not found for user: " + username));
//
//        applicant.setCvFilePath(file);
//        applicantRepository.save(applicant);
    }


}
