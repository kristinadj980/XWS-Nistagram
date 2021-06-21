package com.example.searchmicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchmicroservice.connection.MediaConnection;
import com.example.searchmicroservice.connection.ProfileConnection;
import com.example.searchmicroservice.dto.LocationDTO;
import com.example.searchmicroservice.dto.PostDTO;

@RestController
@RequestMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationController {

	@Autowired
	private final MediaConnection mediaConnection;
	
	@Autowired
	private final ProfileConnection profileConnection;

	public LocationController(MediaConnection mediaConnection, ProfileConnection profileConnection) {
		super();
		this.mediaConnection = mediaConnection;
		this.profileConnection = profileConnection;
	}
	
	@GetMapping("/findPostsByLocation/{location}")
	public ResponseEntity<List<PostDTO>> findPostsByLocation(@PathVariable String location){
		System.out.println("KONTROLER SEARCH BY LOCATION");
		System.out.println(location);
		
		List<PostDTO> posts=mediaConnection.findPostsByLocation(location);
		
		List<String> usernames=profileConnection.getPublicProfiles();
		
		List<PostDTO> publicPosts= new ArrayList<>();
		
		
		System.out.println("LOKACIJEEEEEEEE");
		for(PostDTO p:posts) {
			for(String u:usernames) {
			if(p.getUsername().equals(u)) {
				publicPosts.add(p);
			}
		}
		}
		return new ResponseEntity<List<PostDTO>>(publicPosts, HttpStatus.OK); 
	}

}
