package com.example.mediamicroservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mediamicroservice.dto.MediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.LikePostDTO;

import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.service.implService.ProfileMediaService;
import com.example.mediamicroservice.utils.MediaUpload;

@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
	
	private final PostService postService;
	private final ProfileMediaService profileMediaService;

	@Autowired
	public PostController(PostService postService,ProfileMediaService profileMediaService) {
		super();
		this.postService = postService;
		this.profileMediaService = profileMediaService;
	}
	
	private static String uploadDir = "user-photos";

	@PostMapping("/saveImage")
    public String saveImage(@RequestParam("file") MultipartFile multipartFile ) throws IOException {
		
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", "")); 
        uploadDir = "user-photos";
        MediaUpload.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }
	
	@PostMapping("/addNewPost")
	public ResponseEntity<Post> addNewPost(@RequestBody PostDTO postDTO) {
        Post response = postService.addNewPost(postDTO);
        
		return (ResponseEntity<Post>) (response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response));
		
	}
	

	@GetMapping("/getMyPosts/{username}")
	public ResponseEntity getMyPosts(@PathVariable String username) {
		System.out.println("POGODIOOOOOOOOOOOOOOOOOOOO");
		return new ResponseEntity(postService.findMyPosts(username), HttpStatus.OK); 
		
		//return (ResponseEntity<List<PostDTO>>) (myPosts == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(myPosts));

	}
	
	@PostMapping("/likePost")
	public int likePost(@RequestBody LikePostDTO likePostDTO) {
        int likes = postService.likeThisPost(likePostDTO);
        
		return likes;
		
	}
	
	@GetMapping("/proba")
	public String getProba() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		return "uspesno";
	}
	
}
