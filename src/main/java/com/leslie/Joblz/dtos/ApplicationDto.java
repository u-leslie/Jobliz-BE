package com.leslie.Joblz.dtos;
import com.leslie.Joblz.enums.ApplicationStatus;
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
    private UUID jobSeeker;
    private UUID job;
    private String resume;
    private ApplicationStatus status;
    private Date applicationDate = new Date();
}
