package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.FavouritesDTO;
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
	
	public Post saveAsFavourite(FavouritesDTO favouritesDTO) {
		ProfileMedia profileMedia = profileMediaRepository.findByUsername(favouritesDTO.getMyProfile());
		System.out.println("MOJ PROFIL JE : " + profileMedia.getUsername());
		//prodji kroz moje postove nadji taj i dodaj ga u listu omiljenih
		Post postToSave = new Post();
		List<Post> myPosts = profileMedia.getPosts();
		for (Post post : myPosts) {
			List<Media> medias = post.getMedias();
			for (Media media : medias) {
				if(media.getFileName().equals(favouritesDTO.getFileName())) {
					//to je ta slika
					System.out.println("******OVDEEEE*****");
					List<Post> myFavourites = profileMedia.getFavourites();
					List<Post> favourites = new ArrayList<Post>();
						System.out.println("***************");
						if(myFavourites.size() != 0) {
							//ako vec ima nesto u listi
							 int i=0;
							System.out.println("***************************");
							for (Post p : myFavourites) {
								i++;
								//ako ima proveri da li ima taj sto dodajes
								if(p.getId().equals(post.getId())){
									System.out.println("POST ID " + post.getId());
									System.out.println("POST FAVOURITE ID " + p.getId());
									throw new IllegalArgumentException("You have already added this post in favourites!");
								}else {
									System.out.println("ID OD POSTA " + post.getId());
									favourites.add(p);
									System.out.println("SIZE" + favourites.size());
									if(i<2) {
									favourites.add(post);
									System.out.println("SIZEEE" + favourites.size());
									}
								}
							}
							
						}else {
							//nema jos nista
							System.out.println("NEMA JOS....");
							favourites.add(post);
						}
						
						System.out.println("PROSAOOOOOO");
						profileMedia.setFavourites(favourites);
						System.out.println("PROSAOOOOOO");
						postToSave = post;
						System.out.println(postToSave.getId());
						System.out.println("PROSAOOOOOO");
						try {
							profileMediaRepository.save(profileMedia);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("ERRRRRROR");
							System.out.println(e.getMessage());
						}
						
					
				}
			}
		}
		System.out.println("PROSAOOOOOO");
		return postToSave;
	}
	
}
