package com.leslie.Joblz.mappers;

import com.leslie.Joblz.dtos.ApplicationDto;
import com.leslie.Joblz.entities.Application;

public class ApplicationMapper {
    public static ApplicationDto MapToApplication(Application application) {
       return new ApplicationDto(
               application.getId(),
               application.getJobSeeker(),
               application.getJob(),
               application.getResume(),
               application.getStatus(),
               application.getApplicationDate()
       );
    }

   public static Application MapToApplication(ApplicationDto applicationDto) {
        return new Application(
                applicationDto.getId(),
                applicationDto.getJobSeeker(),
                applicationDto.getJob(),
                applicationDto.getResume(),
                applicationDto.getStatus(),
                applicationDto.getApplicationDate()
        );
   }
}
