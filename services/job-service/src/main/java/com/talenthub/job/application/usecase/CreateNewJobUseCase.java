package com.talenthub.job.application.usecase;

import com.talenthub.job.application.command.JobCommand;
import com.talenthub.job.domain.aggregate.JobAggregate;
import com.talenthub.job.domain.repository.JobRepository;
import com.talenthub.job.domain.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.CommonDataSource;
import java.util.UUID;

// Vinh nois chuyeenj trong lớp
@Service
@RequiredArgsConstructor
public class CreateNewJobUseCase {
    private final JobRepository jobRepository; // DI
    private final CommonDataSource commonDataSource;

    public UUID execute(JobCommand jobCommand) {
        // Business Rules - DB

        if (jobRepository.isExisted(jobCommand.getTitle())) {
            throw new IllegalArgumentException("Can not duplicate " + jobCommand.getTitle());
        }


        // Aggregate - Business Rules
        JobAggregate jobAggregate = JobAggregate.createJob(jobCommand.getTitle(), jobCommand.getDescription(),
                jobCommand.getDepartmentId(), jobCommand.getMinSalary(), jobCommand.getMaxSalary(), jobCommand.getDeadline());

        // Convert from Aggregate to Entity using map struct
        Job job = Job.builder()
                .departmentId(jobAggregate.getDepartmentId())
                .title(jobAggregate.getTitle())
                .description(jobCommand.getDescription())
                .deadline(jobCommand.getDeadline())
                .maxSalary(jobAggregate.getMaxSalary())
                .status(Job.Status.getDraftStatus())
                .build();


        return jobRepository.save(job).getId();

    }
}
