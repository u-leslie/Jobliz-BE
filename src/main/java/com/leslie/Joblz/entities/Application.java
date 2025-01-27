package com.leslie.Joblz.entities;

import com.leslie.Joblz.enums.ApplicationStatus;
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
@Table(name="application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    private User jobSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_id",referencedColumnName = "id",nullable = false)
    private Job job;

    @Column(name="resume",nullable = false)
    private String resume;

    @Column(name="status",nullable = false)
    private ApplicationStatus status;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="application_date",nullable = false)
    private Date applicationDate = new Date();

}
