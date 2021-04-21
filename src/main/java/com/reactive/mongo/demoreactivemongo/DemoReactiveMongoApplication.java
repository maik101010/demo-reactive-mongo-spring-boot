package com.reactive.mongo.demoreactivemongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.reactive.mongo.demoreactivemongo")
@SpringBootApplication
public class DemoReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactiveMongoApplication.class, args);
	}

}
