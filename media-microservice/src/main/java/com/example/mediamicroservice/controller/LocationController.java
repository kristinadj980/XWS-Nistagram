package com.example.mediamicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.service.implService.LocationService;
import com.example.mediamicroservice.service.implService.PostService;

@RestController
@RequestMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {
	
	private final LocationService locationService;
	
	private final PostService postService;
	
	@Autowired
	public LocationController(LocationService locationService,PostService postService) {
		super();
		this.locationService = locationService;
		this.postService=postService;
	}
	
	@GetMapping("/getLocations")
	public ResponseEntity<List<LocationDTO>> getLocations() {

		List<LocationDTO> locations = locationService.getLocations();
		
		return locations == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(locations);
	}
	@GetMapping("/findPostsByLocation/{location}")
	public ResponseEntity findPostsByLocation(@PathVariable String location) {	
		System.out.println("pogodio media-microservice LOKACIJE");
		String[] s=location.split(",");
		LocationDTO loc=new LocationDTO(s[1],s[3],s[0],s[2]);
		List<PostDTO> posts =postService.findPostsByLocation(loc);
		
		for(PostDTO p:posts) {
			System.out.println(p.getUsername());
		}
		
		System.out.println("proso media-microservice LOKACIJE");
		return new ResponseEntity(posts, HttpStatus.OK); 
	}
	
}
