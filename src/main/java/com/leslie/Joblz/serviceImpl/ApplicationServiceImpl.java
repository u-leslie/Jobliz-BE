package com.leslie.Joblz.serviceImpl;
import com.leslie.Joblz.dtos.ApplicationDto;
import com.leslie.Joblz.entities.Application;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.entities.User;
import com.leslie.Joblz.mappers.ApplicationMapper;
import com.leslie.Joblz.repositories.ApplicationRepository;
import com.leslie.Joblz.repositories.JobRepository;
import com.leslie.Joblz.repositories.UserRepository;
import com.leslie.Joblz.services.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    @Override
    public ApplicationDto addApplication(ApplicationDto applicationDto, UUID userId, UUID joBId) {
     User jobSeeker = userRepository.findById(userId)
             .orElseThrow(() -> new RuntimeException("User not found"));
     Job job  = jobRepository.findById(joBId)
             .orElseThrow(() -> new RuntimeException("Job not found"));
        Application application = ApplicationMapper.mapToApplication(applicationDto,jobSeeker,job);
        Application savedApp = applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(savedApp);

    }

    @Override
    public ApplicationDto getApplicationById(UUID applicationId) {
        Application appl = applicationRepository.findById(applicationId).orElseThrow(
                () -> new RuntimeException("Application not found")
        );
                return ApplicationMapper.mapToApplicationDto(appl);
    }

    @Override
    public ApplicationDto updateApplication(UUID applicationId, ApplicationDto updatedApplicationDto) {
     Application appl = applicationRepository.findById(applicationId).orElseThrow(
             () -> new RuntimeException("Application not found")
     );
     appl.setApplicationDate(updatedApplicationDto.getApplicationDate());
     appl.setStatus(updatedApplicationDto.getStatus());
     appl.setResume(updatedApplicationDto.getResume());
     Application savedApp = applicationRepository.save(appl);
     return ApplicationMapper.mapToApplicationDto(savedApp);
    }

    @Override
    public List<ApplicationDto> getAllApplications() {
       List <Application> applications = applicationRepository.findAll();
       return applications.stream().map((application) -> ApplicationMapper.mapToApplicationDto(application)).collect(Collectors.toList());
    }


    @Override
    public void deleteApplication(UUID ApplicationId) {
Application application = applicationRepository.findById(ApplicationId).orElseThrow(
        () -> new RuntimeException("Application not found")
);
applicationRepository.deleteById(ApplicationId);
    }
}
