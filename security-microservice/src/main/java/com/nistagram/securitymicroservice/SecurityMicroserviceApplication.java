package com.nistagram.securitymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
public class SecurityMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityMicroserviceApplication.class, args);
	}

}
