package com.talenthub.job.domain.repository;

import com.talenthub.job.domain.entity.Job;
import com.talenthub.job.infrastructure.specification.JobSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface JobRepository {
    Job save(Job entity);

    Page<Job> search(Pageable pageable, JobSearchCriteria criteria);

    UUID requestApproval(Job job);


    boolean isExisted(String jobTitle);

}
