package com.example.searchmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchmicroservice.connection.ProfileConnection;
import com.example.searchmicroservice.dto.PostDTO;
import com.example.searchmicroservice.service.implService.ProfileService;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
	
	
	private final ProfileService profileService;
	
	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}


	@GetMapping("/searchProfile/{data}")
	public ResponseEntity<List<String>> searchForProfile(@PathVariable String data){
		try {
			return new ResponseEntity<>(profileService.searchForProfile(data), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
