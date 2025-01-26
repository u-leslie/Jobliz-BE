package com.leslie.Joblz.dtos;
import com.leslie.Joblz.enums.JobStatus;
import com.leslie.Joblz.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private UUID id;
    private String title;
    private String description;
    private JobStatus status;
    private JobType type;
    private String location;
    private Long minSalary;
    private Long maxSalary;
    private Date deadline;
    private UUID employerId;

}
