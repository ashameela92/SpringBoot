package com.shameela;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * @class SpringBootRestApplication is main class of SpringBootApp
 */

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.shameela.beans"})
@ComponentScan(basePackageClasses = StudentRegisterController.class)
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
@EntityScan(basePackageClasses = StudentRegistration.class)
public class SpringBootRestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootRestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	 * Using RestTemplate to create resources using POST method during start up
	 */
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			System.out.println("Begin /POST request!");
	    String postUrl = "http://localhost:8083/students";
	    StudentRegistration studentRegistration = new StudentRegistration(123, "Jack", 23, "CSE");
	    ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, studentRegistration, String.class);
	    System.out.println("Response for Post Request: " + postResponse.getStatusCode());
		};
	}

}

