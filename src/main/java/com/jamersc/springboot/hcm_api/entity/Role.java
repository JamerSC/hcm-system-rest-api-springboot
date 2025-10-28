package com.jamersc.springboot.hcm_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // roleName should ideally be unique
    private String roleName;

    // mappedBy refers to the 'roles' field in the User Entity
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    // Permissions associated with Roles
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Or ManyToMany if a user can have multiple roles
    @JoinTable(
            name = "roles_permissions", // Name of the join table
            joinColumns = @JoinColumn(name = "role_id"), // Column in user_roles that refers to user_id
            inverseJoinColumns = @JoinColumn(name = "permission_id") // Column in user_roles that refers to role_id
    )
    private Set<Permission> permissions = new HashSet<>();
}
