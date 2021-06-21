package com.nistagram.profileMicroservice.controller;

import java.util.List;

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

import com.nistagram.profileMicroservice.dto.NotificationDTO;
import com.nistagram.profileMicroservice.service.implService.NotificationService;

@RestController
@RequestMapping(value = "/api/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
	
	private final NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@PostMapping("/postNotify")
	public ResponseEntity<?> postNotify(@RequestBody String username)
	{
		try {
			notificationService.postNotify(username);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/storyNotify")
	public ResponseEntity<?> storyNotify(@RequestBody String username)
	{
		return ResponseEntity.ok(username);
	}

	
	@GetMapping("/getNotifications")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<List<NotificationDTO>> getNotifications() {
		try {
			return new ResponseEntity<>(notificationService.getNotifications(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}




