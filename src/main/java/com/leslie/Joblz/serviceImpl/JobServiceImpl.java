package com.leslie.Joblz.serviceImpl;

import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.enums.JobType;
import com.leslie.Joblz.exceptions.NotFound;
import com.leslie.Joblz.mappers.JobMapper;
import com.leslie.Joblz.repositories.JobRepository;
import com.leslie.Joblz.repositories.UserRepository;
import com.leslie.Joblz.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
    private final UserRepository userRepository;
    private JobRepository jobRepository;

    @Override
    public JobDto addJob(JobDto jobDto) {
        Job job = JobMapper.mapToJob(jobDto);
        Job savedJob = jobRepository.save(job);
        return JobMapper.mapToJobDto(savedJob);
    }

    @Override
    public JobDto getJobById(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(()->
                new NotFound("Job with id not found"+jobId)
                );
        return JobMapper.mapToJobDto(job);
    }

    @Override
    public List <JobDto> getJobByType(JobType jobType) {
       List <Job> jobs = jobRepository.findAllByType(jobType);
       return jobs.stream().map((job)-> JobMapper.mapToJobDto(job))
               .collect(Collectors.toList());
    }

    @Override
    public List<JobDto> getAllJobs() {
        List <Job> jobs = jobRepository.findAll();
        return jobs.stream().map((job)-> JobMapper.mapToJobDto(job))
                .collect(Collectors.toList());
    }

    @Override
    public JobDto updateJob(UUID jobId, JobDto updatedJobDto) {
    Job job = jobRepository.findById(jobId).orElseThrow(
            () -> new NotFound("Job with id not found"+jobId)
    );
    job.setTitle(updatedJobDto.getTitle());
    job.setDescription(updatedJobDto.getDescription());
    job.setType(updatedJobDto.getType());
    job.setStatus(updatedJobDto.getStatus());
    job.setDeadline(updatedJobDto.getDeadline());
    job.setMinSalary(updatedJobDto.getMinSalary());
    job.setMaxSalary(updatedJobDto.getMaxSalary());
    job.setLocation(updatedJobDto.getLocation());
    Job savedJob = jobRepository.save(job);
    return JobMapper.mapToJobDto(savedJob);
    }

    @Override
    public void deleteJob(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new NotFound("Job with id not found"+jobId)
        );
 jobRepository.deleteById(jobId);
    }
}