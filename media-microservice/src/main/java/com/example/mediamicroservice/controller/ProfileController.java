package com.example.mediamicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.EditUsernameDTO;
import com.example.mediamicroservice.dto.FavouritesDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.service.implService.ProfileMediaService;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
	
	private final ProfileMediaService profileMediaService;
	private final PostService postService;
	
	@Autowired
	public ProfileController(ProfileMediaService profileMediaService,PostService postService) {
		this.profileMediaService = profileMediaService;
		this.postService = postService;
	}
	
	@GetMapping("/getUserProfile/{username}")
	public ResponseEntity getUserProfileInfo(@PathVariable String username) {
		return new ResponseEntity(postService.findMyPosts(username), HttpStatus.OK); 
	}
	
	@PostMapping("/saveFavourites")
	public ResponseEntity<Post> savePostAsFavourite(@RequestBody FavouritesDTO favouritesDTO) {
        Post response = profileMediaService.saveAsFavourite(favouritesDTO);
        
		return (ResponseEntity<Post>) (response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response));
		
	}
	
	@PostMapping("/changeUsername")
	public void changeUsername(@RequestBody EditUsernameDTO editUsernameDTO) {
        	profileMediaService.changeUsername(editUsernameDTO);
	}
	
	
}
