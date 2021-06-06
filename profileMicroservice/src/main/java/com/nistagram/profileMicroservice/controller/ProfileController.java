package com.nistagram.profileMicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.dto.RegistredUserDTO;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.service.implService.ProfileService;


@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
	
	private final ProfileService profileServie;
	
	@Autowired
	public ProfileController(ProfileService profileServie) {
		this.profileServie = profileServie;
	}
	
	@GetMapping("/loggedUserInfo")
	@PreAuthorize("hasRole('REGISTRED_USER')")  
	public ResponseEntity<RegistredUserDTO> getLoggedUserInfo() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile user = profileServie.findById(person.getId());
		RegistredUserDTO userInfo = new RegistredUserDTO(user.getId(), user.getUsername());
		
		return userInfo == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(userInfo);
	}
}
