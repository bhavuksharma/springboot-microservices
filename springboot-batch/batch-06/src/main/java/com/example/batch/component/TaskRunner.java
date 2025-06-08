package com.example.batch.component;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

import com.example.batch.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.batch.entity.JobLog;
import com.example.batch.repository.JobLogRepository;

@Component
public class TaskRunner {

    @Value("${report.mail.to}")
    private String reportMailTo;

    @Value("${app.reporting.email.enabled}")
    private boolean isEmailEnabled;

    private final JobLogRepository jobLogRepository;
    private final EmailService emailService;

    public TaskRunner(JobLogRepository jobLogRepository, EmailService emailService) {
        this.jobLogRepository = jobLogRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 20000) // Runs every 20 seconds
    public void runTask(){
        JobLog jobLog = new JobLog();
        jobLog.setStartDateTime(LocalDateTime.now());

        String fileName = "src/main/data/reports/report_" + System.currentTimeMillis() + ".csv";
        File reportFile = new File(fileName);

        reportFile.getParentFile().mkdirs();

        try(FileWriter writer = new FileWriter(reportFile)) {
            
            System.out.println("================== Generating CSV Report =================");
            List<String> userData = List.of("Charlie", "Alice", "Bob");
            // write header
            writer.write("Username,GeneratedAt\n");
            for (String user : userData){
                String line = user + "," + LocalDateTime.now() + "\n";
                writer.write(line);
            }

            // flush data to file
            writer.flush();

            if (isEmailEnabled) {
                // email service
                emailService.sendReport(
                        reportMailTo,
                        "Daily Report",
                        "Find attached the daily user report.",
                        reportFile
                );

                System.out.println("Email sent successfully");
            }else {
                System.out.println("Email sending is disabled via config.");
            }
            
            jobLog.setStatus("Success");
            System.out.println("Report written to: "+ fileName);
        } catch (Exception e) {
            e.getLocalizedMessage();
            jobLog.setStatus("Failure");
        }finally{
            jobLog.setEndDateTime(LocalDateTime.now());
            jobLogRepository.save(jobLog);
            System.out.println("================== Report done =================");
        }

    }
}
