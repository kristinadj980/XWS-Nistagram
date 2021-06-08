package com.nistagram.profileMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.FriendRequestDTO;
import com.nistagram.profileMicroservice.service.implService.FriendRequestService;

@RestController
@RequestMapping(value = "/friendRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendRequestController {
	
	private final FriendRequestService friendRequestService;

	@Autowired
	public FriendRequestController(FriendRequestService friendRequestService) {
		super();
		this.friendRequestService = friendRequestService;
	}
	
	@PostMapping("/newRequest")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity newFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
		try {
			friendRequestService.newFriendRequest(friendRequestDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
