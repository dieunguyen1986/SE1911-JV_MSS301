package com.talenthub.job.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@ToString
public class JobCreateRequest {
    private String title;
    private String description;
    private UUID departmentId;
    private Set<String> requiredSkills = new HashSet<>();
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private LocalDate deadline;
}
