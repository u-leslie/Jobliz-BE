package com.leslie.Joblz.repositories;

import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAllByType(JobType jobType);
}
