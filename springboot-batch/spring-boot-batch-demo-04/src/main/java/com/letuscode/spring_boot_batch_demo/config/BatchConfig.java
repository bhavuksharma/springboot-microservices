package com.letuscode.spring_boot_batch_demo.config;

import com.letuscode.spring_boot_batch_demo.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final ItemReader<Person> reader;
    private final ItemProcessor<Person,Person> processor;
    private final ItemWriter<Person> writer;

    public BatchConfig(ItemReader<Person> reader, ItemProcessor<Person,Person> processor, ItemWriter<Person> writer){
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Bean
    public Job importPersonJob(JobRepository jobRepository, Step step){
        return new JobBuilder("importPersonJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step", jobRepository)
                .<Person,Person>chunk(5, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
