package com.letuscode.springbatchdemo.exception;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomJobExecutionListener implements JobExecutionListener{
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus().isUnsuccessful()) {
			System.out.println("Job Failed with the following exception: "+ jobExecution.getAllFailureExceptions());
		}
		
		if(jobExecution.getStatus() == BatchStatus.FAILED) {
			JobParameters jobParameters = jobExecution.getJobParameters();
			try {
				jobLauncher.run(job, jobParameters);
			} catch (JobExecutionAlreadyRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobRestartException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobInstanceAlreadyCompleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
