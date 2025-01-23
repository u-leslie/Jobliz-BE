package com.leslie.Joblz.entities;

import com.leslie.Joblz.enums.JobStatus;
import com.leslie.Joblz.enums.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="job_desc",nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column (name="status",nullable = false)
    private JobStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="type",nullable = false)
    private JobType type;

    @Column(name="location",nullable = false)
    private String location;

    @Column(name="min_salary",nullable = false)
    private Long minSalary;

    @Column(name="max_salary",nullable = false)
    private Long maxSalary;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deadline",nullable = false)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name="employer_id",nullable = false)
    private User employer;

}