package com.leslie.Joblz.mappers;

import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.entities.Job;


public class JobMapper {
    public static JobDto mapToJobDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getStatus(),
                job.getType(),
                job.getLocation(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getDeadline(),
                job.getEmployer()

        );
    }
    public static Job mapToJob(JobDto jobDto) {
        return new Job(
                jobDto.getId(),
                jobDto.getTitle(),
                jobDto.getDescription(),
                jobDto.getStatus(),
                jobDto.getType(),
                jobDto.getLocation(),
                jobDto.getMinSalary(),
                jobDto.getMaxSalary(),
                jobDto.getDeadline(),
                jobDto.getEmployer()
        );
    }
}
