package com.letuscode.springbatchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	@Bean
	public Job job(JobRepository jobRepository, Step step) {
		return new JobBuilder("job", jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step", jobRepository)
				.<String,String>chunk(10,transactionManager)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.faultTolerant()
				.retry(Exception.class) // Retry for any exception
				.retryLimit(3) // Retry for 3 times
				.skip(SkipException.class) // Skip specific exceptions
				.skipLimit(5) // Skip up to 5 times
				.build();
	}
	
	@Bean
	public ItemReader<String> reader(){
		return new SimpleItemReader();
	}
	
	@Bean
	public ItemProcessor<String, String> processor(){
		return new SimpleItemProcessor();
	}
	
	@Bean
	public ItemWriter<String> writer(){
		return new SimpleItemWriter();
	}
	
	
}
