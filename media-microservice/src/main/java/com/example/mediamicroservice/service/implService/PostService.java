package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LikeDislikeInfoDTO;
import com.example.mediamicroservice.dto.LikePostDTO;
import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.TagDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.service.IPostService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	
	
	private final PostRepository postRepository;
	private final ProfileMediaService profileMediaService;
	private static String uploadDir = "user-photos";

	@Autowired
	public PostService(PostRepository postRepository,ProfileMediaService profileMediaService) {
		super();
		this.postRepository = postRepository;
		this.profileMediaService = profileMediaService;
	}

	@Override
	public Post addNewPost(PostDTO postDTO) {
        Post post = new Post();
        post.setDescription(postDTO.getDescription());
        String locationFront= postDTO.getLocation();
        String parts[] = locationFront.split(",");
        String country = parts[0];
        String city = parts[1];
        String objectName = parts[2];
        String street = parts[3];
        
        Location location = new Location(city,street , country,objectName);
        post.setLocation(location);
        List<TagDTO> tagsDTO = postDTO.getTags();
        List<Tag> tags = new ArrayList<Tag>();
        for (TagDTO t : tagsDTO) {
			Tag tag = new Tag(t.getName());
			tags.add(tag);
		}
        post.setTags(tags);
        Media media = new Media();
        media.setFileName(postDTO.getFileName());
        List<Media> medias = new ArrayList<Media>();
        medias.add(media);
        post.setMedia(medias);
        post.setDate(LocalDateTime.now());
        profileMediaService.addPostToProfile(postDTO, post);
        
        Post p = postRepository.save(post);
		return p;
	}

	@Override
	public List<PostDTO> findMyPosts(String username){
		
		List<PostDTO> myPosts = new ArrayList<PostDTO>();
		ProfileMedia existingProfile = profileMediaService.findByUsername(username);
		System.out.println(existingProfile.getUsername());
		if(existingProfile == null) {
			throw new IllegalArgumentException("Profile doesn't exist!");
		}
		List<Post> posts = existingProfile.getPosts();
		int numberOfLikes = 0;
		int numberOfDislikes = 0;
		for (Post post : posts) {
			List<Media> medias = post.getMedias();
			for (Media m : medias) {
				LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
						post.getLocation().getObjectName());
				if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null) {
					numberOfDislikes = post.getNumberOfDisikes();
					myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO, post.getDate(),0,numberOfDislikes));
				}else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null ) {
					numberOfLikes = post.getNumberOfLikes();
					myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO, post.getDate(),numberOfLikes,0));
				}
				else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null) 
				{
					myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO, post.getDate(),0,0));
				}
				else 
				{
				numberOfLikes = post.getNumberOfLikes();
				numberOfDislikes = post.getNumberOfDisikes();
				myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO, post.getDate(),numberOfLikes,numberOfDislikes));
				}
		}
		}
		
		List<PostDTO> allPosts = getImagesFiles(myPosts);
		return sortByDate(allPosts);
	
	}
	
	@Override
	public List<PostDTO> sortByDate(List<PostDTO> allPosts) {
		//List<PatientSearchDTO> myPatients =  getMyPatients();
			
		 Collections.sort(allPosts, new Comparator<PostDTO>() {
			@Override
			public int compare(PostDTO o1, PostDTO o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		 });
		return allPosts;
	}
	 
	@Override
	 public Integer likeThisPost(LikePostDTO likePostDTO) {
		 ProfileMedia profileMediaTo = profileMediaService.findByUsername(likePostDTO.getUsernameTo());
		 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(likePostDTO.getUsernameFrom());
		 List<Post> myPosts = profileMediaTo.getPosts();
		 List<Media> medias = new ArrayList<Media>();
		 int updatedNumberOfLikes = 0;
		 for (Post post : myPosts) {
			 medias = post.getMedia();
			 for (Media media : medias) {
				if(media.getFileName().equals(likePostDTO.getFileName())) {
					List<ProfileMedia> currentLikes = post.getLikes();
					List<ProfileMedia> currentDislikes = post.getDislikes();
						for (ProfileMedia profileMedia : currentLikes) {
							if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom())) 
								throw new IllegalArgumentException("You have already liked this post!");
						}
						for (ProfileMedia profileMedia : currentDislikes) {
							if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom())) 
								post.setNumberOfDisikes(post.getNumberOfDisikes() - 1);
						}
						
						currentLikes.add(profileMediaFrom);
						post.setLikes(currentLikes);
						int likesNumber = currentLikes.size();
						int currentNumberOfLikes = 0;
						if(post.getNumberOfLikes() == null) {
							updatedNumberOfLikes = likesNumber;
						}else {
						   currentNumberOfLikes = post.getNumberOfLikes();
						   updatedNumberOfLikes = currentNumberOfLikes + 1;
						}
						post.setNumberOfLikes(updatedNumberOfLikes);
						
						postRepository.save(post);
				}
			}
		}
		
		return updatedNumberOfLikes;
	 }
	 
	@Override
	 public Integer dislikeThisPost(LikePostDTO likePostDTO) { 
		 ProfileMedia profileMediaTo = profileMediaService.findByUsername(likePostDTO.getUsernameTo());
		 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(likePostDTO.getUsernameFrom());
		 List<Post> myPosts = profileMediaTo.getPosts();
		 List<Media> medias = new ArrayList<Media>();
		 int updatedNumberOfDislikes= 0;
		 for (Post post : myPosts) {
			 medias = post.getMedia();
			 for (Media media : medias) {
				if(media.getFileName().equals(likePostDTO.getFileName())) {
					List<ProfileMedia> currentDislikes = post.getDislikes();
					List<ProfileMedia> currentLikes = post.getLikes();
					for (ProfileMedia profileMedia : currentDislikes) {
						if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom())) 
							throw new IllegalArgumentException("You have already disliked this post!");
					}
					for (ProfileMedia profileMedia : currentLikes) {
						if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom()))
							post.setNumberOfLikes(post.getNumberOfLikes()- 1);
					}
					currentDislikes.add(profileMediaFrom);
					post.setDislikes(currentDislikes);
					int dislikesNumber = currentDislikes.size();
					int currentNumberOfDislikes = 0;
					if(post.getNumberOfDisikes() == null) {
						updatedNumberOfDislikes = dislikesNumber;
					}else {
						currentNumberOfDislikes = post.getNumberOfDisikes();
						updatedNumberOfDislikes = currentNumberOfDislikes + 1;
					}
					post.setNumberOfDisikes(updatedNumberOfDislikes);
					
					postRepository.save(post);
				}
			}
		}

		return updatedNumberOfDislikes;		
	 }

	public List<PostDTO> getImagesFiles(List<PostDTO> posts) {
		 List<PostDTO> postsDto = new ArrayList<>();
	     if (posts != null) {
	    	 String filePath = new File("").getAbsolutePath();
		     filePath = filePath.concat("/" + uploadDir + "/");
	         for (PostDTO post : posts) {
	        	 postsDto.add(imageFile(post, filePath));
	        }
	    }
	    return postsDto;
	    
	 }


	    public PostDTO imageFile(PostDTO post, String filePath) {
	    	PostDTO postsDto = post;
			List<byte[]> imageBytes = new ArrayList<byte[]>();
			post.setImageBytes(imageBytes);
	        File in = new File(filePath + "/"+post.getFileName());
	        try {
	        	postsDto.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch(NullPointerException n) {
	            n.printStackTrace();
	        }
	        return postsDto;
	    }
	    
	    @Override
	    public List<LikeDislikeInfoDTO> findMyLikes(LikeDislikeInfoDTO dtoInfo){
	    	List<LikeDislikeInfoDTO> profilesWhoLiked = new ArrayList<LikeDislikeInfoDTO>();
	    	ProfileMedia myProfile = profileMediaService.findByUsername(dtoInfo.getMyProfile());
	    	List<ProfileMedia> profiles = new ArrayList<ProfileMedia>();
	    	List<Post> myPosts = myProfile.getPosts();
	    	for (Post post : myPosts) {
				profiles = post.getLikes();
			}
	    	for (ProfileMedia p : profiles) {
				profilesWhoLiked.add(new LikeDislikeInfoDTO(p.getUsername()));
			}
	   
	    	return profilesWhoLiked;
	    }
	    
	    @Override
	    public List<LikeDislikeInfoDTO> findMyDislikes(LikeDislikeInfoDTO dtoInfo){
	    	List<LikeDislikeInfoDTO> profilesWhoDisliked = new ArrayList<LikeDislikeInfoDTO>();
	    	ProfileMedia myProfile = profileMediaService.findByUsername(dtoInfo.getMyProfile());
	    	List<ProfileMedia> profiles = new ArrayList<ProfileMedia>();
	    	List<Post> myPosts = myProfile.getPosts();
	    	for (Post post : myPosts) {
				profiles = post.getDislikes();
			}
	    	for (ProfileMedia p : profiles) {
	    		profilesWhoDisliked.add(new LikeDislikeInfoDTO(p.getUsername()));
			}
	   
	    	return profilesWhoDisliked;
	    }
	
}
