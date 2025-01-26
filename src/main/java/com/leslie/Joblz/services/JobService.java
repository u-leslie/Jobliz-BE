package com.leslie.Joblz.services;

import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobService {
    JobDto addJob(JobDto jobDto,UUID employerId);
    JobDto getJobById(UUID jobId);
    List <JobDto> getJobByType(JobType jobType);
    List<JobDto> getAllJobs();
    JobDto updateJob(UUID jobId, JobDto jobDto);
    void deleteJob(UUID jobId);
}
