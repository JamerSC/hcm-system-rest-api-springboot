package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Applicant applicant;

    // Add this One-to-One relationship to the Employee entity
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id", referencedColumnName = "id")
    private User updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    // Add JPA annotations for createdDate and modifiedDate
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public String getUserFullName() {
        return this.firstName + " " + this.lastName;
    }
}
