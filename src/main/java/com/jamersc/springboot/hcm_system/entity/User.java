package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users") // Good practice to use 'users' as table name for user accounts
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    // Example of roles associated with the user
    @ManyToOne // Or ManyToMany if a user can have multiple roles
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;  // Assuming you have a Role entity for 'APPLICANT', 'MANAGER', 'ADMIN' etc.
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
}
