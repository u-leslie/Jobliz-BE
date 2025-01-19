package com.leslie.Joblz.entities;
import com.leslie.Joblz.enums.UserRole;
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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email" ,nullable= false,unique = true  )
    private String email;

    @Column(name="role",nullable = false)
    private UserRole role = UserRole.JOB_SEEKER;

    @Column(name="profile_pic",nullable = true)
    private String profilePicture;

    @Column(name="password",nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_joined",nullable = false)
    private Date created=new Date() ;


}
