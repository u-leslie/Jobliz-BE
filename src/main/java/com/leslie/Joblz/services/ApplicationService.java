package com.leslie.Joblz.services;

import com.leslie.Joblz.dtos.ApplicationDto;
import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.entities.Application;
import com.leslie.Joblz.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    ApplicationDto addApplication(ApplicationDto applicationDto , UUID userId, UUID joBId);
    ApplicationDto getApplicationById(UUID applicationId);
    ApplicationDto updateApplication(UUID applicationId, ApplicationDto applicationDto);
    List<ApplicationDto> getAllApplications();
    void deleteApplication(UUID ApplicationId);
}
