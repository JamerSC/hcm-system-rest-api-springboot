package com.jamersc.springboot.hcm_system.entity;

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

    // A Role can be assigned to multiple Users
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // mappedBy refers to the 'roles' field in the User entit
    private Set<User> users = new HashSet<>();
}
