package com.talenthub.job.domain.aggregate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JobAggregate {

    public enum Status {
        DRAFT, PENDING_APPROVAL, APPROVED, PUBLISHED, CLOSED
    }

    private String title;
    private String description;
    private UUID departmentId;
    private Set<String> requiredSkills = new HashSet<>();
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private LocalDate deadline;
    private Status status;

    public static JobAggregate createJob(String title, String description, UUID departmentId, BigDecimal minSalary, BigDecimal maxSalary, LocalDate deadline) {
        if (minSalary == null || maxSalary == null || minSalary.signum() < 0 || maxSalary.signum() < 0) {
            throw new IllegalArgumentException("Salary needs to be greater than 0");
        }

        if (minSalary.compareTo(maxSalary) > 0) {
            throw new IllegalArgumentException("minSalary must be <=maxSalary");
        }
        if( deadline.compareTo(LocalDate.now()) < 0 ) {
            throw new IllegalArgumentException("Deadline must be greater than 0");
        }

        JobAggregate job = new JobAggregate();
        job.title = title;
        job.description = description;
        job.departmentId = departmentId;
        job.minSalary = minSalary;
        job.maxSalary = maxSalary;
        job.deadline = deadline;
        job.status = Status.DRAFT;


        return job;
    }

    public void submitForApproval() {
        if (status != Status.DRAFT) {
            throw new IllegalStateException("Only DRAFT can be submitted");
        }

        this.status = Status.PENDING_APPROVAL;

    }

    public void approve() {
        if (status != Status.PENDING_APPROVAL) {
            throw new IllegalStateException("Only PENDING_APPROVAL can be approved");
        }

        this.status = Status.APPROVED;
    }

    public void publish() {
        if (status != Status.APPROVED) {
            throw new IllegalStateException("Only APPROVED can be published");
        }

        this.status = Status.PUBLISHED;
    }

    public void close() {
        this.status = Status.CLOSED;
    }
}
