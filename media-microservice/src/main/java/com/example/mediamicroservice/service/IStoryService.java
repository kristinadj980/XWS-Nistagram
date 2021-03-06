package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Story;

public interface IStoryService {
	List<Story> addNewStory(StoryDTO storyDTO);
	List<StoryDTO> findMyStories(String username);
	List<StoryDTO> findArchiveStories(String username);
	List<StoryDTO> findHighlightedStories(String username);
	//List<StoryDTO> sortByDate(String username);
}
