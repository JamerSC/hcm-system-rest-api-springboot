package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String department;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Job> jobs;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by_employee_id", referencedColumnName = "id")
//    private Employee createdBy;
}
