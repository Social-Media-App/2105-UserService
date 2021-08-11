package com.revature.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author Muhammad_Ibrahim
 * 
 * Main driver
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class Project3MainDriver {

	public static void main(String[] args) {
		SpringApplication.run(Project3MainDriver.class, args);
	}

}
