package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    // Example of roles associated with the user
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Or ManyToMany if a user can have multiple roles
    @JoinTable(
            name = "user_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Column in user_roles that refers to user_id
            inverseJoinColumns = @JoinColumn(name = "role_id") // Column in user_roles that refers to role_id
    )
    private Set<Role> roles = new HashSet<>(); // Initialize to avoid NullPointerException
}
