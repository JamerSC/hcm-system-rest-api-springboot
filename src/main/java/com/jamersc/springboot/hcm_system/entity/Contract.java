package com.jamersc.springboot.hcm_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double salary; // wage a month
    private Date startDate; // contract start date
    private Date endDate; // contract end date
    private int noticePeriod; // 1, 2, or  3...
    // days
    private String workingSchedule; // cms - monthly, quarterly, weekly etc
    private String workingHoursAWeek; // cms of work schedules & hours

    //
    private String salaryStructureType; // cms employee, worker, etc
    private Department department; // name of dept, dropdown, cms
    private String salaryStructure; // ex. different base salary structure for labor, engineer etc
    private Job job; // name of job
    private String contractType; // enums or cms,  dropdown, cms
    private String contractDetails;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
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

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
