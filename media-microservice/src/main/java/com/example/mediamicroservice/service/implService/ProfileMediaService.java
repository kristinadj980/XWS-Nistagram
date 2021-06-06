package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.MediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
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
	
	public List<PostDTO> findMyPosts(String username){
		
		List<PostDTO> myPosts = new ArrayList<PostDTO>();
		ProfileMedia existingProfile = findByUsername(username);
		System.out.println(existingProfile.getUsername());
		if(existingProfile == null) {
			throw new IllegalArgumentException("Profile doesn't exist!");
		}
		List<Post> posts = existingProfile.getPosts();
		for (Post post : posts) {
			List<Media> medias = post.getMedias();
			for (Media m : medias) {
				LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
						post.getLocation().getObjectName());
			
			myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO));
			}
		}
		
		return myPosts;
	}
}
