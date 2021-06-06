package com.example.mediamicroservice.connections;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mediamicroservice.dto.PostDTO;

@FeignClient(name = "eureka-profile-service")
public interface ProfileConnection {
	
	@GetMapping("/getMyPosts/{username}")
	public ResponseEntity<List<PostDTO>> getMyPosts(@PathVariable String username);

}
