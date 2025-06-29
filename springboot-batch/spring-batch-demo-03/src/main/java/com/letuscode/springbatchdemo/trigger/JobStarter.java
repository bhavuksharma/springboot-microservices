package com.letuscode.springbatchdemo.trigger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


// This class is redundant since in new version of spring batch. Job runs automatically on startup
//@Component
public class JobStarter implements CommandLineRunner{
	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	public JobStarter(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();
		
		jobLauncher.run(job, jobParameters);
		System.out.println("Job launched successfully..................");
		
	}

}
