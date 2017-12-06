package com.perfectmatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class PerfectMatchApplication {

	public static void main(String[] args) {

        SpringApplication.run(PerfectMatchApplication.class, args);
	}

}
