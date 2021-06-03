package com.nistagram.profileMicroservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/friendRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendRequestController {
	
}
