package com.nistagram.profileMicroservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.nistagram.profileMicroservice.security.TokenUtils;
import com.nistagram.profileMicroservice.service.implService.PersonService;



@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	private final TokenUtils tokenUtils;

	//private final AuthenticationManager authenticationManager;
	
	private final PersonService personService;
	
	
	@Autowired
	public AuthenticationController(TokenUtils tokenUtils,PersonService personService) {
		this.tokenUtils = tokenUtils;
		//this.authenticationManager = authenticationManager;
		this.personService = personService;
	}

	

}
