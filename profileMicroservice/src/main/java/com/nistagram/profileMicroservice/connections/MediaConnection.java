package com.nistagram.profileMicroservice.connections;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nistagram.profileMicroservice.dto.PostDTO;


@FeignClient(name = "media-microservice")
public interface MediaConnection {
	@GetMapping("/getMyPosts/{username}")
	ResponseEntity<List<PostDTO>> getMyPosts(@PathVariable String username);
	
	@RequestMapping(method=RequestMethod.GET,value="/post/proba",consumes="application/json")
	String getProba();

}
