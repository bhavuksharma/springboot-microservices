package com.example.batch.component;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.batch.entity.JobLog;
import com.example.batch.repository.JobLogRepository;

@Component
public class TaskRunner {

    private final JobLogRepository jobLogRepository;

    public TaskRunner(JobLogRepository jobLogRepository) {
        this.jobLogRepository = jobLogRepository;
    }

    @Scheduled(fixedRate = 20000) // Runs every 20 seconds
    public void runTask(){
        JobLog jobLog = new JobLog();
        jobLog.setStartDateTime(LocalDateTime.now());

        String fileName = "src/main/data/reports/report_" + System.currentTimeMillis() + ".csv";

        try(FileWriter writer = new FileWriter(fileName)) {
            
            System.out.println("================== Generating CSV Report =================");

            List<String> userData = List.of("Charlie", "Alice", "Bob");

            // write header
            writer.write("Username,GeneratedAt\n");

            for (String user : userData){
                String line = user + "," + LocalDateTime.now() + "\n";
                writer.write(line);
            }
            
            jobLog.setStatus("Success");
            System.out.println("Report written to: "+ fileName);
        } catch (Exception e) {
            jobLog.setStatus("Faliure");
        }finally{
            jobLog.setEndDateTime(LocalDateTime.now());
            jobLogRepository.save(jobLog);
            System.out.println("================== Report done =================");
        }

    }
}
