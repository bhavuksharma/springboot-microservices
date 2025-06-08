package com.example.batch;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner {
    
    @Scheduled(fixedRate = 10000) // runs every 10 seconds
    public void runTask(){
        System.out.println("Running batch task at "+ LocalDateTime.now());
    }
}
