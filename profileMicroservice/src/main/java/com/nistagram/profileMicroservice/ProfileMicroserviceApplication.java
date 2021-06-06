package com.nistagram.profileMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ProfileMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileMicroserviceApplication.class, args);
	}

}
