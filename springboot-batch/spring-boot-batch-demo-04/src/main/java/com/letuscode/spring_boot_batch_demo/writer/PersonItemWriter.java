package com.letuscode.spring_boot_batch_demo.writer;

import com.letuscode.spring_boot_batch_demo.model.Person;
import com.letuscode.spring_boot_batch_demo.repository.PersonRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class PersonItemWriter implements ItemWriter<Person> {

    private final PersonRepository personRepository;

    public PersonItemWriter(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        personRepository.saveAll(chunk);
    }
}
