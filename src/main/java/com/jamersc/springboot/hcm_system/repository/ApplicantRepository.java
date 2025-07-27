package com.jamersc.springboot.hcm_system.repository;

import com.jamersc.springboot.hcm_system.entity.Applicant;
import com.jamersc.springboot.hcm_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    // Best practice: Add a method to find Applicant by User ID or User username
    // Since Applicant has a @OneToOne relationship with User, you can query by User's properties.
    // This is crucial for retrieving the *current authenticated user's* applicant profile.
    Applicant findByUser_Username(String username);
    // Or, if you prefer to use the User entity directly:
    Optional<Applicant> findByUser(User user);
}
