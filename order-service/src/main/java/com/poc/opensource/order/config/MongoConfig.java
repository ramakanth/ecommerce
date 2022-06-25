package com.poc.opensource.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
@Configuration
public class MongoConfig {
	@Autowired
	private Environment env;
/*
	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		return mongoTemplate;

	}
	*/
	@Bean 
	public MongoClient mongoClient() { return
	 MongoClients.create(env.getProperty("spring.data.mongodb.uri"));
	}
	@Bean
	 public  MongoTemplate mongoTemplate() { return new
	  MongoTemplate(mongoClient(), "ecom-db"); 
	 }
	 
}
