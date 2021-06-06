package com.nistagram.profileMicroservice.connections;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media-microservice")
public interface MediaConnection {

}
