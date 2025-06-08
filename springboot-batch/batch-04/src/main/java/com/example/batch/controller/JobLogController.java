package com.example.batch.controller;

import com.example.batch.entity.JobLog;
import com.example.batch.repository.JobLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/joblogs")
public class JobLogController {
    private JobLogRepository jobLogRepository;

    @Autowired
    public JobLogController(JobLogRepository jobLogRepository){
        this.jobLogRepository = jobLogRepository;
    }

    @GetMapping
    public List<JobLog> getLogs(){
        return jobLogRepository.findAll();
    }

    @GetMapping("/{id}")
    public JobLog getLogById(@PathVariable long id){
        return jobLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }
}
