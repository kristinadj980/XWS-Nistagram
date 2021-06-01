package com.example.mediamicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdministratorController {
	
	@GetMapping("/hello")
    ResponseEntity<String> getMyAccount()
    {
        
        System.out.println("ghj");
        return (ResponseEntity<String>) ResponseEntity.ok();
    }
}
