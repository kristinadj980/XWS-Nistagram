package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.LikePostDTO;
import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
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
       /* List<TagDTO> tagsDTO = postDTO.getTags();
        List<Tag> tags = new ArrayList<Tag>();
        for (TagDTO t : tagsDTO) {
			Tag tag = new Tag(t.getName());
			tags.add(tag);
		}
        post.setTags(tags);*/
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
		for (Post post : posts) {
			List<Media> medias = post.getMedias();
			for (Media m : medias) {
				LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
						post.getLocation().getObjectName());
			
				myPosts.add(new PostDTO(post.getDescription(),username,m.getFileName(),locationDTO, post.getDate()));
			}
		}
		
		List<PostDTO> allPosts = getImagesFiles(myPosts);
		
		return sortByDate(allPosts);
	}

	 private List<PostDTO> sortByDate(List<PostDTO> allPosts) {
		//List<PatientSearchDTO> myPatients =  getMyPatients();
			
		 Collections.sort(allPosts, new Comparator<PostDTO>() {
			@Override
			public int compare(PostDTO o1, PostDTO o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		 });
		return allPosts;
	}
	 
	 public Integer likeThisPost(LikePostDTO likePostDTO) {
		 //ko je lajkovao znamo iz toga je ko je ulogovan
		 //prvo nadji profil po username
		 ProfileMedia profileMediaTo = profileMediaService.findByUsername(likePostDTO.getUsernameTo());
		 System.out.println("PROFIL KOME LAJKUJE " + profileMediaTo.getUsername());
		 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(likePostDTO.getUsernameFrom());
		 System.out.println("PROFIL KOJI LAJKUJE " + profileMediaFrom.getUsername());
		 //dobavi njegove postove
		 List<Post> myPosts = profileMediaTo.getPosts();
		 List<Media> medias = new ArrayList<Media>();
		 List<ProfileMedia> likes = new ArrayList<ProfileMedia>();
		 Post likedPost = new Post();
		 System.out.println("*****************************");
		 //nadji onaj post za poslatu sliku
		 for (Post post : myPosts) {
			 medias = post.getMedia();
			 for (Media media : medias) {
				if(media.getFileName().equals(likePostDTO.getFileName())) {
					//ako je to ta slika setuj lajkove za nju
					System.out.println("##########################3");
					ProfileMedia media2 = new ProfileMedia();
					likes.add(profileMediaFrom);
					post.setLike(likes);
					likedPost = post;
				}
			}
		}
		int likesNumber = likes.size();
		int currentNumberOfLikes = 0;
		int updatedNumberOfLikes = 0;
		if(likedPost.getNumberOfLikes() == null) {
			System.out.println("nema lajkova jos");
			updatedNumberOfLikes = likesNumber;
		}else {
			System.out.println("ima lajkova jos");
			currentNumberOfLikes = likedPost.getNumberOfLikes();
			System.out.println("CURRENT" + currentNumberOfLikes);
		   updatedNumberOfLikes = currentNumberOfLikes + likesNumber;
		}
		likedPost.setLikes(likes);
		likedPost.setNumberOfLikes(updatedNumberOfLikes);
		
		postRepository.save(likedPost);
		
		System.out.println("BROJ LAJOKVA" + likesNumber);
		
		return updatedNumberOfLikes;
		
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
	
}
