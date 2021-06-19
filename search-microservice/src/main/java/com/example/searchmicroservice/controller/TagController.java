package com.example.searchmicroservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.searchmicroservice.connection.ProfileConnection;
import com.example.searchmicroservice.dto.LoggedUserDTO;
import com.example.searchmicroservice.dto.PostDTO;
import com.example.searchmicroservice.model.Post;
import com.example.searchmicroservice.model.Tag;
import com.sun.el.stream.Stream;



@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
	
	@Autowired
	private final MediaConnection mediaConnection;
	
	@Autowired
	private final ProfileConnection profileConnection;
	
	@Autowired
	public TagController(MediaConnection mediaConnection,ProfileConnection profileConnection) {
		super();
		this.mediaConnection = mediaConnection;
		this.profileConnection=profileConnection;
	}

	@GetMapping("/findPostsByTag/{tag}")
	public ResponseEntity<List<PostDTO>> findPostsByTag(@PathVariable String tag){
		System.out.println("KONTROLER SEARCH BY TAG");
		System.out.println(tag);
		
		
		List<PostDTO> posts=mediaConnection.findPostsByTag(tag);
		
		List<String> usernames=profileConnection.getPublicProfiles();
		
		
		//List<String> privateUsernames=profileConnection.getPrivateProfiles(user.getId());
		
		List<PostDTO> publicPosts= new ArrayList<>();
		
		//List<String> usernames1=new ArrayList<>(usernames);
		//usernames1.addAll(privateUsernames);
		
		
		
		System.out.println("ISPOD IFA SAM");
		for(PostDTO p:posts) {
			for(String u:usernames) {
			if(p.getUsername().equals(u)) {
				publicPosts.add(p);
			}
		}
		}
		return new ResponseEntity<List<PostDTO>>(publicPosts, HttpStatus.OK); 
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
