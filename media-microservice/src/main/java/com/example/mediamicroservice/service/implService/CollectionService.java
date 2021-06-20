package com.example.mediamicroservice.service.implService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.CollectionDTO;
import com.example.mediamicroservice.dto.CollectionInfoDTO;
import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.TagDTO;
import com.example.mediamicroservice.model.FavouritesCollection;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.CollectionRepository;
import com.example.mediamicroservice.repository.ProfileMediaRepository;
import com.example.mediamicroservice.service.ICollectionService;

@Service
public class CollectionService implements ICollectionService{
	private final PostService postService;
	private final ProfileMediaService profileMediaSevice;
	private final CollectionRepository collectionRepository;
	private final ProfileMediaRepository profileMediaRepositoty;
	
	
	@Autowired
	public CollectionService(PostService postService,ProfileMediaService profileMediaSevice,CollectionRepository collectionRepository,
			ProfileMediaRepository profileMediaRepositoty) {
		this.postService = postService;
		this.profileMediaSevice = profileMediaSevice;
		this.collectionRepository = collectionRepository;
		this.profileMediaRepositoty = profileMediaRepositoty;
	}
	
	public String addToCollection(CollectionDTO collectionDTO) {
		Post post = postService.findById(collectionDTO.getPostId());
		ProfileMedia myProfile = profileMediaSevice.findByUsername(collectionDTO.getMyProfile());
		List<FavouritesCollection> myCollections = myProfile.getFavouritesCollections();
		FavouritesCollection favouritesCollection = new FavouritesCollection();
		if(collectionDTO.getSelectedCollection() == null) {
			List<Post> posts = new ArrayList<Post>();
			posts.add(post);
			favouritesCollection.setName(collectionDTO.getCollectionName());
			favouritesCollection.setPosts(posts);
			myProfile.getFavouritesCollections().add(favouritesCollection);
			collectionRepository.save(favouritesCollection);
			profileMediaRepositoty.save(myProfile);
			return collectionDTO.getCollectionName();
			
		}else 
			for (FavouritesCollection f : myCollections) 
				if(f.getId().equals(collectionDTO.getSelectedCollection())) {
					f.getPosts().add(post);
					collectionRepository.save(f);
				}
		return " ";
	}
	
	public List<CollectionInfoDTO> findMyCollections(String username){
		ProfileMedia profile = profileMediaRepositoty.findByUsername(username);
		List<FavouritesCollection> myCollections = profile.getFavouritesCollections();
		List<CollectionInfoDTO> collectionInfoDTOs = new ArrayList<CollectionInfoDTO>();
		
		int numberOfLikes = 0;
		int numberOfDislikes = 0;
		int numberOfComments = 0;
		for (FavouritesCollection f : myCollections) {
			List<Post> posts = f.getPosts();
			CollectionInfoDTO collectionInfoDTO = new CollectionInfoDTO();
			List<PostDTO> myPosts = new ArrayList<PostDTO>();
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
					
				List<PostDTO> allPosts = postService.getImagesFiles(myPosts);
				postService.sortByDate(allPosts);
				
				collectionInfoDTO.setPosts(allPosts);
				
			}
			collectionInfoDTO.setName(f.getName());
			collectionInfoDTO.setId(f.getId());
			collectionInfoDTOs.add(collectionInfoDTO);
			
		}
		
	    return collectionInfoDTOs;
	}

}
