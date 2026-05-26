package com.talenthub.job.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "job_skills",
       uniqueConstraints = @UniqueConstraint(columnNames = {"job_id", "skill_id"}))
@EqualsAndHashCode(of = {"jobId", "skillId"})
public class JobSkill {

    public enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "job_id", nullable = false)
    private UUID jobId;

    @Column(name = "skill_id", nullable = false)
    private UUID skillId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Level requiredLevel;

    @Column(name = "is_mandatory", nullable = false)
    private boolean mandatory;

}
