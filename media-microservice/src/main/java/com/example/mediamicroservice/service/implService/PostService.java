package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.connections.ProfileConnection;
import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.CommentNotifyDTO;
import com.example.mediamicroservice.dto.ImageDTO;
import com.example.mediamicroservice.dto.ImagesFrontDTO;
import com.example.mediamicroservice.dto.InapropriateContentDTO;
import com.example.mediamicroservice.dto.LikeDislikeHistoryDTO;
import com.example.mediamicroservice.dto.LikeDislikeInfoDTO;
import com.example.mediamicroservice.dto.LikePostDTO;
import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.TagDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.InappropriateContent;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.RequestStatus;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.InappropriateContentRepository;
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
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	
	
	private final PostRepository postRepository;
	private final ProfileMediaRepository profileMediaRepository;
	private final ProfileMediaService profileMediaService;
	private static String uploadDir = "user-photos";
	private final TagRepository tagRepository;
	private final InappropriateContentRepository inappropriateContentRepository;
	@Autowired
	private ProfileConnection profileConnection;

	@Autowired
	public PostService(PostRepository postRepository, ProfileMediaRepository profileMediaRepository,
			ProfileMediaService profileMediaService, TagRepository tagRepository,
			InappropriateContentRepository inappropriateContentRepository) {
		super();
		this.postRepository = postRepository;
		this.profileMediaRepository = profileMediaRepository;
		this.profileMediaService = profileMediaService;
		this.tagRepository = tagRepository;
		this.inappropriateContentRepository = inappropriateContentRepository;
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
        List<Media> medias = new ArrayList<Media>();
        for(String s:postDTO.getFileNames()) {
        	Media media = new Media();
        	media.setFileName(s);
        	medias.add(media);
        }
        post.setMedia(medias);
        post.setDate(LocalDateTime.now());
        profileMediaService.addPostToProfile(postDTO, post);
        
        Post p = postRepository.save(post);
        profileConnection.postNotify(postDTO.getUsername());
		return p;
	}

	@Override
	public List<PostDTO> findMyPosts(String username){
		List<PostDTO> myPosts = new ArrayList<PostDTO>();
		ProfileMedia existingProfile = profileMediaService.findByUsername(username);
		if(existingProfile == null) 
			throw new IllegalArgumentException("Profile doesn't exist!");
		List<Post> posts = existingProfile.getPosts();
		int numberOfLikes = 0; 
		int numberOfDislikes = 0;
		int numberOfComments = 0;
		for (Post post : posts) {
			List<Media> medias = post.getMedias();
			List<String> mediasFileName = new ArrayList<String>();
			for (Media m : medias) {
				mediasFileName.add(m.getFileName());
			}
			LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
					post.getLocation().getObjectName());
			List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
			List<Tag> tags = post.getTags();
			for (Tag tag : tags) {
				tagsDTO.add(new TagDTO(tag.getName()));
			}
			if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() == null) {
				numberOfDislikes = post.getNumberOfDisikes();
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId()));
			}else if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() != null) {
					numberOfDislikes = post.getNumberOfDisikes();
					numberOfComments = post.getNumberOfComments();
					myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,numberOfComments,tagsDTO,post.getId()));
			
			}else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
				numberOfLikes = post.getNumberOfLikes();
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,0,tagsDTO,post.getId()));
			
		    }else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() != null ) {
			numberOfLikes = post.getNumberOfLikes();
			numberOfComments = post.getNumberOfComments();
			myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,numberOfComments,tagsDTO,post.getId()));
		    }
		    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() == null && post.getNumberOfComments() == null ) {
		    	numberOfDislikes = post.getNumberOfDisikes();
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId()));
		    }
		    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
				numberOfLikes = post.getNumberOfLikes();
				numberOfDislikes = post.getNumberOfDisikes();
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,0,tagsDTO,post.getId()));
			    }
			else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() == null) 
			{
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,0,tagsDTO,post.getId()));
			}
			else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() != null) 
			{
				numberOfComments = post.getNumberOfComments();
				myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,numberOfComments,tagsDTO,post.getId()));
			}
			else 
			{
			numberOfLikes = post.getNumberOfLikes();
			numberOfDislikes = post.getNumberOfDisikes();
			numberOfComments = post.getNumberOfComments();
			myPosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,numberOfComments,tagsDTO,post.getId()));
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
			if(likePostDTO.getPostId() == post.getId()) {
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
			 if(likePostDTO.getPostId() == post.getId()) {
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
			List<ImageDTO> images = new ArrayList<>();
			PostDTO postsDto = post;
			
			for(String fileName:post.getFileNames()) {
				ImageDTO imageDTO = new ImageDTO();
				List<byte[]> bytes = new ArrayList<byte[]>();
				imageDTO.setImageBytes(bytes);
		        File in = new File(filePath + "/"+fileName);
		        try {
		        	bytes.add(IOUtils.toByteArray(new FileInputStream(in)));
		        	imageDTO.setImageBytes(bytes);
		        	images.add(imageDTO);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }catch(NullPointerException n) {
		            n.printStackTrace();
		        }
	        }
			postsDto.setImages(images);
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
	    	System.out.println("*******************************");
	    	for (Post post : myPosts) {
	    		System.out.println("*******************************");
				profiles = post.getDislikes();
				System.out.println(profiles.size());
			}
	    	for (ProfileMedia p : profiles) {
	    		profilesWhoDisliked.add(new LikeDislikeInfoDTO(p.getUsername()));
	    		System.out.println(profilesWhoDisliked.size());
			}
	   
	    	System.out.println("*************OVDE******************");
	    	return profilesWhoDisliked;
	    }
	    
	    @Override
	    public void commentPost(LikePostDTO dto) {
	    	 ProfileMedia profileMediaTo = profileMediaService.findByUsername(dto.getUsernameTo());
			 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(dto.getUsernameFrom());
			 List<Post> myPosts = profileMediaTo.getPosts();
			 List<Media> medias = new ArrayList<Media>();
			 int updatedNumberOfComments = 0;
			 for (Post post : myPosts) {
				 if(dto.getPostId() == post.getId()) {
						 List<Comment> currentComments = post.getComments();
						 List<Comment> comments = new ArrayList<Comment>();
						 if(currentComments.size() != 0) {
						 int i=0;
						 for (Comment comment : currentComments) {
							 i++;
							 comments.add(comment);
							 if(i<2) {
							    comments.add(new Comment(dto.getComment(), profileMediaFrom, LocalDateTime.now()));
							}
						  }
						 }else {
							 comments.add(new Comment(dto.getComment(), profileMediaFrom,LocalDateTime.now()));
						 }
						 int numberOfComments = comments.size();
						 int currentNumberOfComments = 0;
						 if(post.getNumberOfComments() == null) {
							 updatedNumberOfComments = numberOfComments;
						 }else {
							 currentNumberOfComments = post.getNumberOfComments();
							 updatedNumberOfComments = currentNumberOfComments + 1;
						 }
						 post.setComments(comments);
						 post.setNumberOfComments(updatedNumberOfComments);
						 postRepository.save(post);
						 CommentNotifyDTO c = new CommentNotifyDTO(dto.getUsernameTo(), dto.getUsernameFrom());
						 try {
						 profileConnection.commentNotify(c);
						 }catch(Exception e) {
							 System.out.println(e.getMessage());
						 }
						 
					 }
				 }
			}
	    
	    @Override
	    public List<LikePostDTO> findMyComments(LikePostDTO dtoInfo){
	    	List<LikePostDTO> profilesWhoCommented = new ArrayList<LikePostDTO>();
	    	ProfileMedia myProfile = profileMediaService.findByUsername(dtoInfo.getUsernameTo());
	    	List<Comment> comments = new ArrayList<Comment>();
	    	List<Comment> allComments = new ArrayList<Comment>();
	    	List<Post> myPosts = myProfile.getPosts();
	    	for (Post post : myPosts) {
	    		comments = post.getComments();
	    		allComments = sortCommentsByDate(comments);
	    		for (Comment c : comments) {
	    			ProfileMedia user =  c.getRegistredUserProfile();
					profilesWhoCommented.add(new LikePostDTO(user.getUsername(), c.getDescription(),c.getAnswer(), comments.size(), c.getId()));
				}
			}
	   
	    	return profilesWhoCommented;
	    }
	    
	    @Override
	    public List<Comment> sortCommentsByDate(List<Comment> comments) {	
			 Collections.sort(comments, new Comparator<Comment>() {
				@Override
				public int compare(Comment o1, Comment o2) {
					return o2.getDate().compareTo(o1.getDate());
				}
			 });
			 
			return comments;
		}
	    
	    public List<PostDTO> findMyFavouritePosts(String username){
	    	List<PostDTO> myFavouritePosts = new ArrayList<PostDTO>();
			ProfileMedia existingProfile = profileMediaService.findByUsername(username);
			System.out.println(existingProfile.getUsername());
			if(existingProfile == null) 
				throw new IllegalArgumentException("Profile doesn't exist!");
			List<Post> favourites = existingProfile.getFavourites();
			int numberOfLikes = 0;
			int numberOfDislikes = 0;
			int numberOfComments = 0;
			for (Post post : favourites) {
				List<Media> medias = post.getMedias();
				List<String> mediasFileName = new ArrayList<String>();
				for (Media m : medias) {
					mediasFileName.add(m.getFileName());
				}
				LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
						post.getLocation().getObjectName());
				List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
				List<Tag> tags = post.getTags();
				for (Tag tag : tags) {
					tagsDTO.add(new TagDTO(tag.getName()));
				}
				if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() == null) {
					numberOfDislikes = post.getNumberOfDisikes();
					myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId()));
					
				}else if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() != null) {
						numberOfDislikes = post.getNumberOfDisikes();
						numberOfComments = post.getNumberOfComments();
						myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,numberOfComments,tagsDTO,post.getId()));
				
				}else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
					numberOfLikes = post.getNumberOfLikes();
					myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,0,tagsDTO,post.getId()));
				
			    }else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() != null ) {
				numberOfLikes = post.getNumberOfLikes();
				numberOfComments = post.getNumberOfComments();
				myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,numberOfComments,tagsDTO,post.getId()));
			    }
			    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() == null && post.getNumberOfComments() == null ) {
			    	numberOfDislikes = post.getNumberOfDisikes();
			    	myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId()));
			    }
			    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
					numberOfLikes = post.getNumberOfLikes();
					numberOfDislikes = post.getNumberOfDisikes();
					myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,0,tagsDTO,post.getId()));
				    }
				else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() == null) 
				{
					myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,0,tagsDTO,post.getId()));
				}
				else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() != null) 
				{
					numberOfComments = post.getNumberOfComments();
					myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,numberOfComments,tagsDTO,post.getId()));
				}
				else 
				{
				numberOfLikes = post.getNumberOfLikes();
				numberOfDislikes = post.getNumberOfDisikes();
				numberOfComments = post.getNumberOfComments();
				myFavouritePosts.add(new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,numberOfComments,tagsDTO,post.getId()));
				}
			}
			
			List<PostDTO> allPosts = getImagesFiles(myFavouritePosts);
			return sortByDate(allPosts);
			
		}

		@Override
		public Post findById(Long id) {
			return postRepository.findById(id).get();
		}
		
		public InappropriateContent reportPost(InapropriateContentDTO dto) {
	    	 ProfileMedia profileMediaTo = profileMediaService.findByUsername(dto.getUsernameTo());
			 ProfileMedia profileMediaFrom =profileMediaService.findByUsername(dto.getUsernameFrom());
			 InappropriateContent inapropriateContent = new InappropriateContent();
			 List<Post> myPosts = profileMediaTo.getPosts();
			 for (Post post : myPosts) {
				 if(dto.getPostId() == post.getId()) {
						inapropriateContent.setDescription(dto.getDescription());
						inapropriateContent.setPost(post);
						inapropriateContent.setStatus(RequestStatus.sent);
						inapropriateContent.setProfileToId(profileMediaTo.getId());
						inapropriateContent.setProfileFromId(profileMediaFrom.getId());
				 }
			}
			 
	     return inappropriateContentRepository.save(inapropriateContent);
		}
		
		@Override
		public List<LikeDislikeHistoryDTO> getMyLikeDislikeHistory(String username) {
			List<LikeDislikeHistoryDTO> historyDTOs = new ArrayList<LikeDislikeHistoryDTO>();
			ProfileMedia myProfile = profileMediaService.findByUsername(username);
			List<Post> allPosts = postRepository.findAll();
			List<ProfileMedia> allProfiles = profileMediaService.findAll();
			for (Post post : allPosts) {
				List<ProfileMedia> likes = post.getLikes();
				List<ProfileMedia> dislikes = post.getDislikes();
				for (ProfileMedia p : likes) {
					if(p.getId() == myProfile.getId()) {
							for (ProfileMedia profileMedia : allProfiles) {
								List<Post> posts = profileMedia.getPosts();
								for (Post pt : posts) {
  								  if(pt.getId() == post.getId()) {
										historyDTOs.add(new LikeDislikeHistoryDTO(profileMedia.getUsername(),null,post.getId()));
								}
						    }
					 }
				}
			}
			for (ProfileMedia p : dislikes) {
					if(p.getId() == myProfile.getId()) {
							for (ProfileMedia profileMedia : allProfiles) {
								List<Post> posts = profileMedia.getPosts();
								for (Post pt : posts) {
									if(pt.getId() == post.getId()) {
										historyDTOs.add(new LikeDislikeHistoryDTO(null,profileMedia.getUsername(),post.getId()));
									}
						 }
					}
				}
			}
			}
			return historyDTOs;
		}
		
		@Override
		public List<PostDTO> findPostPictureById(Long postId, String username) {
			Post post = postRepository.findById(postId).get();
			PostDTO postDTO = new PostDTO();
			List<PostDTO> posts = new ArrayList<PostDTO>();
			int numberOfLikes = 0;
			int numberOfDislikes = 0;
			int numberOfComments = 0;
			List<Media> medias = post.getMedias();
			List<String> mediasFileName = new ArrayList<String>();
			for (Media m : medias) {
				mediasFileName.add(m.getFileName());
			}
			LocationDTO locationDTO = new LocationDTO(post.getLocation().getCity(), post.getLocation().getStreet(),post.getLocation().getCountry(),
					post.getLocation().getObjectName());
			List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
			List<Tag> tags = post.getTags();
			for (Tag tag : tags) {
				tagsDTO.add(new TagDTO(tag.getName()));
			}
			if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() == null) {
				numberOfDislikes = post.getNumberOfDisikes();
				postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId());
				
			}else if( post.getNumberOfLikes() == null && post.getNumberOfDisikes() != null && post.getNumberOfComments() != null) {
					numberOfDislikes = post.getNumberOfDisikes();
					numberOfComments = post.getNumberOfComments();
					postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,numberOfComments,tagsDTO,post.getId());
			
			}else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
				numberOfLikes = post.getNumberOfLikes();
				postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,0,tagsDTO,post.getId());
			
		    }else if (post.getNumberOfDisikes() == null && post.getNumberOfLikes() != null && post.getNumberOfComments() != null ) {
			numberOfLikes = post.getNumberOfLikes();
			numberOfComments = post.getNumberOfComments();
			postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,0,numberOfComments,tagsDTO,post.getId());
		    }
		    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() == null && post.getNumberOfComments() == null ) {
		    	numberOfDislikes = post.getNumberOfDisikes();
		    	postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,numberOfDislikes,0,tagsDTO,post.getId());
		    }
		    else if (post.getNumberOfDisikes() != null && post.getNumberOfLikes() != null && post.getNumberOfComments() == null ) {
				numberOfLikes = post.getNumberOfLikes();
				numberOfDislikes = post.getNumberOfDisikes();
				postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,0,tagsDTO,post.getId());
			    }
			else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() == null) 
			{
				postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,0,tagsDTO,post.getId());
			}
			else if(post.getNumberOfLikes() == null && post.getNumberOfDisikes() == null && post.getNumberOfComments() != null) 
			{
				numberOfComments = post.getNumberOfComments();
				postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),0,0,numberOfComments,tagsDTO,post.getId());
			}
			else 
			{
			numberOfLikes = post.getNumberOfLikes();
			numberOfDislikes = post.getNumberOfDisikes();
			numberOfComments = post.getNumberOfComments();
			postDTO = new PostDTO(post.getDescription(),username,mediasFileName,locationDTO, post.getDate(),numberOfLikes,numberOfDislikes,numberOfComments,tagsDTO,post.getId());
			}
			posts.add(postDTO);
			List<PostDTO> allPosts = getImagesFiles(posts);
			return sortByDate(allPosts);
		}

}
