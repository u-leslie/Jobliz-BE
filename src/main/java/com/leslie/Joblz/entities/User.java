package com.leslie.Joblz.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leslie.Joblz.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email" ,nullable= false,unique = true  )
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private UserRole role= UserRole.JOB_SEEKER;

    @Column(name="profile_pic",nullable = true)
    private String profilePicture;

    @Column(name="password",nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_joined",nullable = false)
    private Date created=new Date() ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
    return getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
