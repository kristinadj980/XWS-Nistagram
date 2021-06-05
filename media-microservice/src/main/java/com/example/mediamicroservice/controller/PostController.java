package com.example.mediamicroservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Person;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.service.implService.PostService;
import com.example.mediamicroservice.utils.MediaUpload;


@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
	
	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
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
	
}
