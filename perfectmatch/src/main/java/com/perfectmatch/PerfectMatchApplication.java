package com.perfectmatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.perfectmatch.web.configuration.PerfectMatchWebConfig;
import com.perfectmatch.web.configuration.PerfectMatchSecurityConfig;

import org.springframework.context.annotation.Import;
@SpringBootApplication
@EnableReactiveMongoRepositories
@Import({
  PerfectMatchWebConfig.class,
  PerfectMatchSecurityConfig.class
})
public class PerfectMatchApplication {

	public static void main(String[] args) {

        SpringApplication.run(PerfectMatchApplication.class, args);
	}

}
