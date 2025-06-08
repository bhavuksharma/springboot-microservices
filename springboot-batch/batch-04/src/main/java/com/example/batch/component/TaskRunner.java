package com.example.batch.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.batch.entity.JobLog;
import com.example.batch.repository.JobLogRepository;

@Component
public class TaskRunner {

    private JobLogRepository jobLogRepository;

    public TaskRunner(JobLogRepository jobLogRepository) {
        this.jobLogRepository = jobLogRepository;
    }

    @Scheduled(fixedRate = 20000) // Runs every 20 seconds
    public void runTask(){
        JobLog jobLog = new JobLog();
        jobLog.setStartDateTime(LocalDateTime.now());

        try {
            
            System.out.println("================== Task Started =================");

            List<String> userData = List.of("Charlie", "Alice", "Bob");
            userData.stream()
                    .forEach(user -> {
                        System.out.println("Generating report for user: " + user);
                    });
            
            jobLog.setStatus("Success");
        } catch (Exception e) {
            jobLog.setStatus("Faliure");
        }finally{
            jobLog.setEndDateTime(LocalDateTime.now());
            jobLogRepository.save(jobLog);
            System.out.println("================== Task Ended =================");
        }

    }
}
