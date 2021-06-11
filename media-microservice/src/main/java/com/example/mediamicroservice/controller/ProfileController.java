package com.example.mediamicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.service.implService.ProfileMediaService;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
	
	private final ProfileMediaService profileMediaService;
	private final PostService postService;
	
	public ProfileController(ProfileMediaService profileMediaService,PostService postService) {
		this.profileMediaService = profileMediaService;
		this.postService = postService;
	}
	
	@GetMapping("/getUserProfile/{username}")
	public ResponseEntity getUserProfileInfo(@PathVariable String username) {
		System.out.println("************TRAZI******************");
		
		return new ResponseEntity(postService.findMyPosts(username), HttpStatus.OK); 
	}
	
}
