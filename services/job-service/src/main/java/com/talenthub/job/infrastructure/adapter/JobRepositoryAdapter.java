package com.talenthub.job.infrastructure.adapter;

import com.talenthub.job.domain.repository.JobRepository;
import com.talenthub.job.domain.entity.Job;
import com.talenthub.job.infrastructure.persistence.JobJpaRepository;
import com.talenthub.job.infrastructure.specification.JobSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JobRepositoryAdapter implements JobRepository {
    private final JobJpaRepository jobJpaRepository;

    @Override
    public Job save(Job job) {
        return jobJpaRepository.save(job);
    }

    @Override
    public Page<Job> search(Pageable pageable, JobSearchCriteria criteria) {
        return null;
    }

    @Override
    public UUID requestApproval(Job job) {
        return jobJpaRepository.save(job).getId();
    }

    @Override
    public boolean isExisted(String jobTitle) {
        return jobJpaRepository.existsByJobTitle(jobTitle);
    }
}
