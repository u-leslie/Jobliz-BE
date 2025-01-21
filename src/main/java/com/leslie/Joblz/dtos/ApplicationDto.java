package com.leslie.Joblz.dtos;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.entities.User;
import com.leslie.Joblz.enums.ApplicationStatus;
import com.leslie.Joblz.enums.UserRole;
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
public class ApplicationDto {
    private UUID id;
    private User jobSeeker;
    private Job job;
    private String resume;
    private ApplicationStatus status;
    private Date applicationDate = new Date();
}
