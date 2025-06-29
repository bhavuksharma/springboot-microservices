package com.example.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@PostMapping("/importData")
	public String jobLauncher() {
		
		final JobParameters jobParameters = new JobParametersBuilder()
													.addLong("startAt", System.currentTimeMillis()).toJobParameters();
		
		try {		
			// launch the job
			final JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			
			// Return job status
			return jobExecution.getStatus().toString();
		} catch (Exception e) {
			return "job failed with the execption: " + e.getLocalizedMessage();
		}
	}
}
