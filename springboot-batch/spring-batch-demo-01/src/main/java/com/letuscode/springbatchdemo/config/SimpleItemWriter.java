package com.letuscode.springbatchdemo.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleItemWriter implements ItemWriter<String> {

	@Override
	public void write(Chunk<? extends String> chunk){
		chunk.forEach(data -> System.out.println(data));
	}

}
