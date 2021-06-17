package com.example.mediamicroservice.controller;

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

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.service.implService.TagService;

@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
	
	@Autowired
	private final TagService tagService;
	@Autowired
	private final PostService postService;
	

	public TagController(TagService tagService, PostService postService) {
		super();
		this.tagService = tagService;
		this.postService = postService;
	}

	
	
	@GetMapping("/getAllTags")
	public ResponseEntity<List<Tag>> getAllTags() {	
		List<Tag> tags = tagService.findAll();
		
		return (ResponseEntity<List<Tag>>) (tags == null
				? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(tags));
	}
	
	@GetMapping("/findPostsByTag/{tag}")
	public ResponseEntity findPostsByTag(@PathVariable String tag) {	
		System.out.println("pogodio media-microservice");
		List<PostDTO> posts =postService.findPostsByTag(tag);
		
		for(PostDTO p:posts) {
			System.out.println(p.getUsername());
		}
		
		System.out.println("proso media-microservice");
		return new ResponseEntity(posts, HttpStatus.OK); 
	}
	
}
