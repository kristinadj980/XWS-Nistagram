package com.nistagram.profileMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.service.implService.VerificationRequestService;

@RestController
@RequestMapping(value = "/api/request", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VerificationRequestController {
	
	private final VerificationRequestService verificationRequestService;
	
	@Autowired
	public VerificationRequestController( VerificationRequestService verificationRequestService) {
		this.verificationRequestService = verificationRequestService;
	}
	
	@PostMapping("/acceptRequest")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> acceptVerificationRequest(@RequestBody VerificationRequestDTO requestDTO) {
		try {
			verificationRequestService.acceptRequest(requestDTO);
			return new ResponseEntity<>("Request successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/rejectRequest")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> rejectVerificationRequest(@RequestBody VerificationRequestDTO requestDTO) {
		try {
			verificationRequestService.rejectRequest(requestDTO);
			return new ResponseEntity<>("Request successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
}
