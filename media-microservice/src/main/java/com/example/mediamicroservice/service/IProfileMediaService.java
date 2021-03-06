package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.EditUsernameDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.Story;

public interface IProfileMediaService {
	ProfileMedia findByUsername(String username);
	void addPostToProfile(PostDTO postDTO, Post post);
	void addStoryToProfile(StoryDTO storyDTO, Story story);
	void changeUsername(EditUsernameDTO editUsernameDTO);
	List<ProfileMedia> findAll();
}
