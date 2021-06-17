package com.example.searchmicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchmicroservice.connection.MediaConnection;
import com.example.searchmicroservice.dto.PostDTO;
import com.example.searchmicroservice.model.Post;
import com.example.searchmicroservice.model.Tag;



@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
	
	@Autowired
	private final MediaConnection mediaConnection;
	
	@Autowired
	public TagController(MediaConnection mediaConnection) {
		super();
		this.mediaConnection = mediaConnection;
	}

	@GetMapping("/findPostsByTag/{tag}")
	public ResponseEntity<List<PostDTO>> findPostsByTag(@PathVariable String tag){
		System.out.println("KONTROLER SEARCH BY TAG");
		System.out.println(tag);
		List<Tag> allTags=mediaConnection.getAllTags();
		
		List<PostDTO> posts=mediaConnection.findPostsByTag(tag);
		
		System.out.println("ISPOD IFA SAM");
		for(PostDTO p:posts) {
			System.out.println(p.getDate().toString());
		}
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK); 
	}

	
	@GetMapping("/getAllTags")
	public ResponseEntity<List<Tag>> getAllTags() {	
		System.out.println("Search-Tag controler");
		List<Tag> allTags=mediaConnection.getAllTags();
		for(Tag t:allTags) {
			System.out.println(t.getName());
		}
		return (ResponseEntity<List<Tag>>) (allTags == null
				? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(allTags));
	}

}
