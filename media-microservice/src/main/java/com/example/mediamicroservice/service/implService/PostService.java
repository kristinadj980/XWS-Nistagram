package com.example.mediamicroservice.service.implService;

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
import com.example.mediamicroservice.repository.ProfileMediaRepository;
import com.example.mediamicroservice.repository.TagRepository;
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
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	
	
	private final PostRepository postRepository;
	private final ProfileMediaRepository profileMediaRepository;
	private final ProfileMediaService profileMediaService;
	private static String uploadDir = "user-photos";
	private final TagRepository tagRepository;
	
	@Autowired
	public PostService(PostRepository postRepository, ProfileMediaRepository profileMediaRepository,
			ProfileMediaService profileMediaService, TagRepository tagRepository) {
		super();
		this.postRepository = postRepository;
		this.profileMediaRepository = profileMediaRepository;
		this.profileMediaService = profileMediaService;
		this.tagRepository = tagRepository;
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
	 
	 public Integer likeThisPost(LikePostDTO likePostDTO) {
		 //ko je lajkovao znamo iz toga je ko je ulogovan
		 //prvo nadji profil po username
		 ProfileMedia profileMediaTo = profileMediaService.findByUsername(likePostDTO.getUsernameTo());
		 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(likePostDTO.getUsernameFrom());
		 //dobavi njegove postove
		 List<Post> myPosts = profileMediaTo.getPosts();
		 List<Media> medias = new ArrayList<Media>();
		 List<ProfileMedia> likes = new ArrayList<ProfileMedia>();
		 List<ProfileMedia> currentLikes = new ArrayList<ProfileMedia>();
		 Post likedPost = new Post();
		 //nadji onaj post za poslatu sliku
		 for (Post post : myPosts) {
			 medias = post.getMedia();
			 for (Media media : medias) {
				if(media.getFileName().equals(likePostDTO.getFileName())) {
					//ako je to ta slika setuj lajkove za nju
					System.out.println("##########################3");
					//proveri da li je vec lajkovao ovaj koji hoce da lajkuje
					currentLikes = post.getLikes();
					for (ProfileMedia profileMedia : currentLikes) {
						if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom())) {
							//ako je vec lajkovao
							throw new IllegalArgumentException("You have already liked this post!");
						}
					}
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
	 
	 public Integer dislikeThisPost(LikePostDTO likePostDTO) {  //smanji br lajkova a povecas dislajkove
		 //ko je lajkovao znamo iz toga je ko je ulogovan
		 //prvo nadji profil po username
		 ProfileMedia profileMediaTo = profileMediaService.findByUsername(likePostDTO.getUsernameTo());
		 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(likePostDTO.getUsernameFrom());
		 //dobavi njegove postove
		 List<Post> myPosts = profileMediaTo.getPosts();
		 List<Media> medias = new ArrayList<Media>();
		 List<ProfileMedia> dislikes = new ArrayList<ProfileMedia>();
		 List<ProfileMedia> currentDislikes = new ArrayList<ProfileMedia>();
		 Post dislikedPost = new Post();
		 //nadji onaj post za poslatu sliku
		 for (Post post : myPosts) {
			 medias = post.getMedia();
			 for (Media media : medias) {
				if(media.getFileName().equals(likePostDTO.getFileName())) {
					//ako je to ta slika setuj lajkove za nju
					System.out.println("##########################3");
					//proveri da li je vec lajkovao ovaj koji hoce da lajkuje
					currentDislikes = post.getDislikes();
					for (ProfileMedia profileMedia : currentDislikes) {
						if(profileMedia.getUsername().equals(likePostDTO.getUsernameFrom())) {
							//ako je vec dislajkovao
							throw new IllegalArgumentException("You have already disliked this post!");
						}
					}
					ProfileMedia media2 = new ProfileMedia();
					dislikes.add(profileMediaFrom);
					post.setDislikes(dislikes);
					dislikedPost = post;
				}
			}
		}
		int dislikesNumber = dislikes.size();
		int currentNumberOfDislikes = 0;
		int updatedNumberOfDislikes = 0;
		if(dislikedPost.getNumberOfDisikes() == null) {
			System.out.println("nema dislajkova jos");
			updatedNumberOfDislikes = dislikesNumber;
		}else {
			System.out.println("ima dislajkova jos");
			
			currentNumberOfDislikes = dislikedPost.getNumberOfDisikes();
			System.out.println("CURRENT" + currentNumberOfDislikes);
		   updatedNumberOfDislikes = currentNumberOfDislikes + dislikesNumber;
		}
		dislikedPost.setDislikes(dislikes);
		dislikedPost.setNumberOfDisikes(updatedNumberOfDislikes);
		
		postRepository.save(dislikedPost);
		
		System.out.println("BROJ DISLAJOKVA" + dislikesNumber);
		
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
		public List<PostDTO> findPostsByTag(String tag) {
			
			System.out.println("U Post SERVISU FIND BY TAG");
			System.out.println(tag);
			List<PostDTO> dto=new ArrayList<>();
			List<PostDTO> posts=findAllPosts();
			
			//Tag t=tagRepository.findByName(tag);
			//System.out.println(t.getName());
			TagDTO t1=new TagDTO(tag);
			System.out.println(t1.getName());
			
			for(PostDTO p:posts) {
				for(TagDTO td:p.getTags()) {
					if(td.getName().equals(t1.getName())) {
						dto.add(p);
						System.out.println(p.getUsername());
					}
				}
			}
			System.out.println("Kraaaaaj post servisa");
			return dto;
			
		}





		@Override
		public List<PostDTO> findAllPosts() {
			List<Post> posts=new ArrayList<>();
			posts=postRepository.findAll();
			List<PostDTO> postsDTO=new ArrayList<>();
			int numberOfLikes = 0;
			int numberOfDislikes = 0;
			for (Post post : posts) {
				
				List<Media> medias = post.getMedias();
				for (Media m : medias) {
					LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
							post.getLocation().getObjectName());
					List<TagDTO> tagsDTO=new ArrayList<>();
					for(Tag t:post.getTags()) {
						tagsDTO.add(new TagDTO(t.getName()));
					
					}
					if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null) {
						numberOfDislikes = post.getNumberOfDisikes();
						postsDTO.add(new PostDTO(post.getDescription(),tagsDTO,profileMediaRepository.findByPostId(post.getId()),m.getFileName(),locationDTO, post.getDate(),0,numberOfDislikes));
					}else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null ) {
						numberOfLikes = post.getNumberOfLikes();
						postsDTO.add(new PostDTO(post.getDescription(),tagsDTO,profileMediaRepository.findByPostId(post.getId()),m.getFileName(),locationDTO, post.getDate(),numberOfLikes,0));
					}
					else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null) 
					{
						postsDTO.add(new PostDTO(post.getDescription(),tagsDTO,profileMediaRepository.findByPostId(post.getId()),m.getFileName(),locationDTO, post.getDate(),0,0));
					}
					else 
					{
					numberOfLikes = post.getNumberOfLikes();
					numberOfDislikes = post.getNumberOfDisikes();
					postsDTO.add(new PostDTO(post.getDescription(),tagsDTO,profileMediaRepository.findByPostId(post.getId()),m.getFileName(),locationDTO, post.getDate(),numberOfLikes,numberOfDislikes));
					}
			}
			}
			
			List<PostDTO> allPosts = getImagesFiles(postsDTO);
			return allPosts;
		}





		@Override
		public List<PostDTO> findPostsByLocation(LocationDTO location) {
			System.out.println("U Post SERVISU FIND BY LOK=CATION");
			System.out.println(location.getCity());
			List<PostDTO> dto=new ArrayList<>();
			List<PostDTO> posts=findAllPosts();
			
			/*
			String[] s=location.split(",");
			
			LocationDTO loc=new LocationDTO();
			loc.setCountry(s[0]);
			loc.setCity(s[1]);
			loc.setStreet(s[2]);
			loc.setObjectName(s[3]);
			*/
			for(PostDTO p:posts) {
				 if(p.getLocationDTO().getCountry().equals(location.getCountry()) && p.getLocationDTO().getCity().equals(location.getCity()) &&
						 p.getLocationDTO().getStreet().equals(location.getStreet()) && p.getLocationDTO().getObjectName().equals(location.getObjectName())) {
						dto.add(p);
						System.out.println(p.getUsername());
					}
				}
			
			System.out.println("Kraj post servisa za lokacije");
			return dto;

		
		}	
	
}
