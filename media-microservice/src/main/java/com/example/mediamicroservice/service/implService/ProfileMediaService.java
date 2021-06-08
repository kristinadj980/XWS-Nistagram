package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.MediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.repository.ProfileMediaRepository;
import com.example.mediamicroservice.service.IProfileMediaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileMediaService implements IProfileMediaService {

	private final ProfileMediaRepository profileMediaRepository;
	
	@Autowired
	public ProfileMediaService(ProfileMediaRepository profileMediaRepository) {
		this.profileMediaRepository = profileMediaRepository;
	}
	@Override
	public ProfileMedia findByUsername(String username) {
		return profileMediaRepository.findByUsername(username);
	}
	
	@Override
	public void addPostToProfile(PostDTO postDTO, Post post) {
		ProfileMedia profileMedia = new ProfileMedia();
        ProfileMedia existingProfile = findByUsername(postDTO.getUsername());
        List<Post> posts = new ArrayList<Post>();
        
        if(existingProfile != null) {
        	
            posts = existingProfile.getPosts();
        	posts.add(post);
        	existingProfile.setPosts(posts);
        	
        }else {
        	profileMedia.setUsername(postDTO.getUsername());
        	posts.add(post);
        	profileMedia.setPosts(posts);
        	profileMediaRepository.save(profileMedia);
        }
	}
	@Override
	public void addStoryToProfile(StoryDTO storyDTO, Story story) {
		ProfileMedia profileMedia = new ProfileMedia();
        ProfileMedia existingProfile = findByUsername(storyDTO.getUsername());
        List<Story> stories = new ArrayList<Story>();
        
        if(existingProfile != null) {
        	
            stories = existingProfile.getStories();
        	stories.add(story);
        	existingProfile.setStories(stories);
        	
        }else {
        	profileMedia.setUsername(storyDTO.getUsername());
        	stories.add(story);
        	profileMedia.setStories(stories);
        	profileMediaRepository.save(profileMedia);
        }
		
	}
	
}
