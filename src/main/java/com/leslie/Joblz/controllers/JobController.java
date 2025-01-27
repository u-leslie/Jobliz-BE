package com.leslie.Joblz.controllers;

import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.enums.JobType;
import com.leslie.Joblz.services.JobService;
import com.leslie.Joblz.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAuthority('EMPLOYER')")
@RequestMapping("/api/v1/jobs")
public class JobController {
private JobService jobService;
private JwtService jwtService;

    @PreAuthorize("hasAuthority('EMPLOYER')")
    @PostMapping("/create")
    public ResponseEntity<JobDto> create(
            @RequestBody JobDto jobDto,
            @RequestHeader("Authorization") String token
    ) {
        String subToken = token.substring(7);
        UUID employerId = UUID.fromString(jwtService.extractUsername(subToken));
        String role = jwtService.extractRole(subToken);
        System.out.println("Auth token: " + token);
        System.out.println("Employer ID: " + employerId);
        System.out.println("Role: " + role);
        if (employerId == null || !"EMPLOYER".equals(role)) {
            System.out.println("Invalid employer ID or role");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        JobDto savedJob = jobService.addJob(jobDto, employerId);
        return ResponseEntity.ok(savedJob);
    }

 // get job by id
 @GetMapping("/getById/{id}")
 public ResponseEntity<JobDto> getById(@PathVariable("id") UUID id) {
        JobDto jobDto = jobService.getJobById(id);
        return ResponseEntity.ok(jobDto);
 }

    // get job by id
    @GetMapping("/getByType/{type}")
    public ResponseEntity<JobDto> getById(@PathVariable("type") JobType type) {
        List <JobDto> jobs = jobService.getJobByType(type);
        return ResponseEntity.ok((JobDto) jobs);
    }

 //get all users
    @GetMapping("/getAllJobs")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        List<JobDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
    //update user by id
    @PutMapping("updateById/{id}")
    public ResponseEntity<JobDto> updateById(@PathVariable("id") UUID id, @RequestBody JobDto jobDto) {
        JobDto savedJob = jobService.updateJob(id, jobDto);
        return ResponseEntity.ok(savedJob);
    }

    //delete job by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }
}
