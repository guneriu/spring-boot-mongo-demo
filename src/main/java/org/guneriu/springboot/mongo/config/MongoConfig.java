package org.guneriu.springboot.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {
	
	private static String MONGO_HOST = "mongo.host";
	private static String MONGO_PORT = "mongo.port";
	private static String MONGO_DB = "mongo.db";
	
	@Autowired
	Environment enviroment;

	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(enviroment.getRequiredProperty(MONGO_HOST), Integer.valueOf(enviroment.getRequiredProperty(MONGO_PORT)));
	}

	@Bean
	public MongoOperations mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), enviroment.getRequiredProperty(MONGO_DB));
	}

}
