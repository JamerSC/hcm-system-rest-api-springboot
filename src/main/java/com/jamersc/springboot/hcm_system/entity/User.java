package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password; // Stored as hashed password
    @Column(unique = true, nullable = false)
    private String email;
    //@ManyToOne
    //@JoinColumn(name = "role_id", nullable = false)
    private String role; // change to Role class  // Assuming you have a Role entity for 'APPLICANT', 'MANAGER', 'ADMIN' etc.
    private String firstName;
    private String lastName;
}
