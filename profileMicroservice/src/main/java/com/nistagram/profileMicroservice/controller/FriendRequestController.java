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
@RequestMapping(value = "/api/friendRequest", produces = MediaType.APPLICATION_JSON_VALUE)
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
			return new ResponseEntity<>(friendRequestService.newFriendRequest(friendRequestDTO),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getMyRequests")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity getMyRequests() {
		return new ResponseEntity<>(friendRequestService.getMyRequests(),HttpStatus.OK);
	}
	
	
	@PostMapping("/requestResponse")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity setRequestResponse(@RequestBody FriendRequestDTO friendRequestDTO) {
		try {
			return new ResponseEntity<>(friendRequestService.setRequestResponse(friendRequestDTO),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/cancelRrequest")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity cancelRrequest(@RequestBody FriendRequestDTO friendRequestDTO) {
		try {
			friendRequestService.cancelRequest(friendRequestDTO);
			return new ResponseEntity<>("Request is canceled!",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/unfollow")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity unfollowFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
		try {
			friendRequestService.unfollowFriend(friendRequestDTO);
			return new ResponseEntity<>("You have unfollowed this account!",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/removeFollowera")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity removeFollowera(@RequestBody FriendRequestDTO friendRequestDTO) {
		try {
			friendRequestService.removeFollowera(friendRequestDTO);
			return new ResponseEntity<>("You have removed this account from followers!",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
