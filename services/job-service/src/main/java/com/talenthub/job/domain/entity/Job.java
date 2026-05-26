package com.talenthub.job.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "jobs")
@Builder
public class Job {

    public enum Status {
        DRAFT, PENDING_APPROVAL, APPROVED, PUBLISHED, CLOSED;

        public static Status getDraftStatus() {
            return DRAFT;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private UUID departmentId;
    private Set<String> requiredSkills = new HashSet<>();
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private LocalDate deadline;
    private Status status;

}
