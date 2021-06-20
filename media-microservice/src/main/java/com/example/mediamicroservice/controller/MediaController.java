package com.example.mediamicroservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.MediaService;

@RestController
@RequestMapping(value = "/media", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MediaController {
	
	private final MediaService mediaService;
	
	@Autowired
	public MediaController(MediaService mediaService) {
		this.mediaService = mediaService;
	}
	
	@PostMapping("/saveDocument")
	public ResponseEntity<Media> saveVerificationDocument(@RequestBody String fileName) {
        Media media = mediaService.saveDocumentImage(fileName);
		return (ResponseEntity<Media>) (media == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(media));
		
	}
}

