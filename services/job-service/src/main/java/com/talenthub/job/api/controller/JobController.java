package com.talenthub.job.api.controller;

import com.talenthub.job.api.dto.JobCreateRequest;
import com.talenthub.job.application.command.JobCommand;
import com.talenthub.job.application.usecase.CreateNewJobUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.TaskExecutionOutcome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final CreateNewJobUseCase createNewJobUseCase;

    @PostMapping
    public Map<?, ?> createJob(@Valid @RequestBody JobCreateRequest jobCreateRequest){
        createNewJobUseCase.execute(new JobCommand(jobCreateRequest.getTitle(),
                jobCreateRequest.getDescription(), jobCreateRequest.getDepartmentId(), jobCreateRequest.getRequiredSkills(), jobCreateRequest.getMinSalary(), jobCreateRequest.getMaxSalary(), jobCreateRequest.getDeadline()));

        return Map.of("status", HttpStatus.CREATED.value());
    }


    private JobCommand fromDto(JobCreateRequest jobCreateRequest){
        return new JobCommand();
    }
}
