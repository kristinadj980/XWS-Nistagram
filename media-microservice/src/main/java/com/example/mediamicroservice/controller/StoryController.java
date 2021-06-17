package com.example.mediamicroservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mediamicroservice.connections.ProfileConnection;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.service.implService.ProfileMediaService;
import com.example.mediamicroservice.service.implService.StoryService;
import com.example.mediamicroservice.utils.MediaUpload;

@RestController
@RequestMapping(value = "/story", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoryController {
	
	private final ProfileMediaService profileMediaService;
	private final StoryService storyService;
	private final ProfileConnection profileConnection;
	
	@Autowired
	public StoryController(ProfileMediaService profileMediaService, StoryService storyService,ProfileConnection profileConnection) {
		super();
		this.profileMediaService = profileMediaService;
		this.storyService = storyService;
		this.profileConnection=profileConnection;
	}
	
	private static String uploadDir = "user-photos";
	
	@PostMapping("/saveImage")
    public String saveImage(@RequestParam("file") MultipartFile multipartFile ) throws IOException {
		
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", "")); 
        uploadDir = "user-photos";
        MediaUpload.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }
	
	@PostMapping("/addNewStory")
	public ResponseEntity<Story> addNewStory(@RequestBody StoryDTO storyDTO) {
        Story response = storyService.addNewStory(storyDTO);
        
		return (ResponseEntity<Story>) (response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(response));
		
	}
	

	@GetMapping("/getMyStories/{username}")
	public ResponseEntity getMyStories(@PathVariable String username) {
		
		return new ResponseEntity(storyService.findMyStories(username), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getArchiveStories/{username}")
	public ResponseEntity getArchiveStories(@PathVariable String username) {
		
		return new ResponseEntity(storyService.findArchiveStories(username), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getHighlightedStories/{username}")
	public ResponseEntity getHighlightedStories(@PathVariable String username) {
		
		return new ResponseEntity(storyService.findHighlightedStories(username), HttpStatus.OK); 
		
	}
	
	@GetMapping("/proba")
	public ResponseEntity<String> Proba() {
		 System.out.println("AAAAAAA");
        String proba=profileConnection.proba();
        System.out.println("AAAAAAA");
		return ResponseEntity.ok(proba);
		
	}
}
