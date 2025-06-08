package com.example.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.batch.entity.JobLog;

@Repository
public interface JobLogRepository extends JpaRepository<JobLog, Long> {
    
}
