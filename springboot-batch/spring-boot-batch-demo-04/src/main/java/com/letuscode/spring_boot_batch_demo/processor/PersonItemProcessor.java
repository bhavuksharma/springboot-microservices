package com.letuscode.spring_boot_batch_demo.processor;

import com.letuscode.spring_boot_batch_demo.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person thePerson) throws Exception {
        thePerson.setFirstName(thePerson.getFirstName().toUpperCase());
        thePerson.setLastName(thePerson.getLastName().toUpperCase());
        return thePerson;
    }
}
