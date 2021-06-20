package com.nistagram.profileMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.dto.FriendNotificationsDTO;
import com.nistagram.profileMicroservice.service.implService.FriendNotificationsService;

@RestController
@RequestMapping(value = "/api/friendNotificationsController", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendNotificationsController {

	private final FriendNotificationsService friendNotificationsService;

	@Autowired
	public FriendNotificationsController(FriendNotificationsService friendNotificationsService) {
		super();
		this.friendNotificationsService = friendNotificationsService;
	}
	
	@GetMapping("/friendNotifications/{username}")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<FriendNotificationsDTO> getfriendNotifications(@PathVariable String username) {
		
		try {
			return new ResponseEntity(friendNotificationsService.getfriendNotifications(username), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
