package com.leslie.Joblz.controllers;

import com.leslie.Joblz.dtos.ApplicationDto;
import com.leslie.Joblz.dtos.JobDto;
import com.leslie.Joblz.services.ApplicationService;
import com.leslie.Joblz.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAuthority('JOB_SEEKER')")
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<ApplicationDto> create(@RequestBody ApplicationDto applicationDto, @RequestHeader("Authorization") String token) {
        String subToken = token.substring(7);
        UUID seekerId = UUID.fromString(jwtService.extractUsername(subToken));
        String role = jwtService.extractRole(subToken);
        System.out.println("Auth token: " + token);
        System.out.println("Job seeker ID: " + seekerId);
        System.out.println("Role: " + role);
        if (seekerId == null || !"JOB_SEEKER".equals(role)) {
            System.out.println("Invalid Seeker ID or role");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
     UUID jobId = applicationDto.getJob();
        ApplicationDto savedAppl = applicationService.addApplication(applicationDto,seekerId,jobId);
        return ResponseEntity.ok(savedAppl);

    }

    // application by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApplicationDto> getById(@PathVariable("id") UUID applicationId) {
        ApplicationDto application = applicationService.getApplicationById(applicationId);
        return ResponseEntity.ok(application);
    }

    // get all applications
    @GetMapping("getAllApps")
    public ResponseEntity<List<ApplicationDto>> getAllApps() {
        List<ApplicationDto> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<ApplicationDto> updateById(@PathVariable("id") UUID applicationId, @RequestBody ApplicationDto applicationDto) {
        ApplicationDto savedAppl = applicationService.updateApplication(applicationId,applicationDto);
        return ResponseEntity.ok(savedAppl);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID applicationId) {
        applicationService.deleteApplication(applicationId);
        return new ResponseEntity<>("Application deleted successfully",HttpStatus.OK);
    }
}
