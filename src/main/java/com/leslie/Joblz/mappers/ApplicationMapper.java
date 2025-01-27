package com.leslie.Joblz.mappers;

import com.leslie.Joblz.dtos.ApplicationDto;
import com.leslie.Joblz.entities.Application;
import com.leslie.Joblz.entities.Job;
import com.leslie.Joblz.entities.User;
import static com.leslie.Joblz.mappers.JobMapper.mapToJobDto;
import static com.leslie.Joblz.mappers.UserMapper.mapToUserDto;

public class ApplicationMapper {
    public static ApplicationDto mapToApplicationDto(Application application) {
       return new ApplicationDto(
               application.getId(),
               application.getJobSeeker().getId(),
               application.getJob().getId(),
               application.getResume(),
               application.getStatus(),
               application.getApplicationDate()
       );
    }

   public static Application mapToApplication(ApplicationDto applicationDto , User jobSeeker , Job job) {
        return new Application(
                applicationDto.getId(),
                jobSeeker,
                job,
                applicationDto.getResume(),
                applicationDto.getStatus(),
                applicationDto.getApplicationDate()
        );
   }
}
