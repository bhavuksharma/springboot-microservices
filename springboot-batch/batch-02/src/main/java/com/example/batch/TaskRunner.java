package com.example.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner {
    
    @Scheduled(fixedRate = 20000) // runs every 20 seconds
    public void runTask(){
        System.out.println("======= Starting report generation at " + java.time.LocalDateTime.now());

        // 1. fetch data (simulated)
        List<String> userData = List.of("karthik", "suresh", "ramesh", "mahesh");

        // 2. process and generate report
        for(String user: userData){
            System.out.println("User: " + user + " - Report generated at " + java.time.LocalDateTime.now());
        }

        // 3. end Task
        System.out.println("======= Report generation completed at " + java.time.LocalDateTime.now());
    }
}
