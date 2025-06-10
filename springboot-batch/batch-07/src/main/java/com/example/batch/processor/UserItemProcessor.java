package com.example.batch.processor;

import com.example.batch.entity.User;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User,User> {
    @Override
    public User process(User user) throws Exception {
        user.setName(user.getName().toUpperCase());  // example transformation
        return user;
    }
}
