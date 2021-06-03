package com.nistagram.profileMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.security.TokenUtils;
import com.nistagram.profileMicroservice.service.implService.PersonService;
import com.nistagram.profileMicroservice.service.implService.ProfileService;



@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
	
	private final TokenUtils tokenUtils;
	private final ProfileService profileService;

	@Autowired
	public AuthorityController(TokenUtils tokenUtils,ProfileService profileService) {
		this.tokenUtils = tokenUtils;
		this.profileService = profileService;
	}
	
	// Endpoint za registraciju novog korisnika
		@PostMapping("/register")
		public ResponseEntity<String> registerUser(@RequestBody PersonRequestDTO userRequest, UriComponentsBuilder ucBuilder) {
			
	       if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
	        throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
	       }
	       Person existingUser = profileService.findByEmail(userRequest.getEmail());
	       if (existingUser != null) {
	    	   throw new IllegalArgumentException("Email already exists!");
	       }
	       Person user = profileService.save(userRequest);
	    
	       return new ResponseEntity<>("User is successfully registred!", HttpStatus.CREATED);
		}

	

}