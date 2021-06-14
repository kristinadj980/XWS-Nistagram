package com.example.mediamicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.CollectionDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.CollectionService;

@RestController
@RequestMapping(value = "/collection", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionController {
	
	private final CollectionService collectionService;
	
	public CollectionController(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	@PostMapping("/addToCollection")
	public ResponseEntity<String> addToCollection(@RequestBody CollectionDTO collectionDTO) {
        String response = collectionService.addToCollection(collectionDTO);
        
		return (ResponseEntity<String>) (response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response));
		
	}
	
	@GetMapping("/getMyCollections/{username}")
	public ResponseEntity getMyCollections(@PathVariable String username) {
		
		return new ResponseEntity(collectionService.findMyCollections(username), HttpStatus.OK); 

	}
	

}
