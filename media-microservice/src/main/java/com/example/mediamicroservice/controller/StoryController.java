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
	
	@Autowired
	public StoryController(ProfileMediaService profileMediaService, StoryService storyService) {
		super();
		this.profileMediaService = profileMediaService;
		this.storyService = storyService;
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
	
}
