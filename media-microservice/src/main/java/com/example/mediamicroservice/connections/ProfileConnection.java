package com.example.mediamicroservice.connections;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "eureka-profile-service")
public interface ProfileConnection {

}
