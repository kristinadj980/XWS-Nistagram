package com.example.mediamicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.connections.ProfileConnection;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class MediaMicroserviceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MediaMicroserviceApplication.class, args);
	}

}
