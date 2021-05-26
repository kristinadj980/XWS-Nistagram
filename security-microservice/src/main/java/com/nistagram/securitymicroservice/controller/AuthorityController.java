package com.nistagram.securitymicroservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.securitymicroservice.security.TokenUtils;



@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
	
	private final TokenUtils tokenUtils;

	@Autowired
	public AuthorityController(TokenUtils tokenUtils) {
		this.tokenUtils = tokenUtils;
	}

	

}