package com.talenthub.job.infrastructure.persistence;

import com.talenthub.job.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobJpaRepository extends JpaRepository<Job, UUID> {
    boolean existsByJobTitle(String jobTitle);

}
