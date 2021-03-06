package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.connections.ProfileConnection;
import com.example.mediamicroservice.dto.ImageDTO;
import com.example.mediamicroservice.dto.LocationDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.ProfileDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.dto.TagDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.StoryRepository;
import com.example.mediamicroservice.service.IStoryService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService implements IStoryService {
	
	private final StoryRepository storyRepository;
	private final ProfileMediaService profileMediaService;
	private final String uploadDir="user-photos";
	@Autowired
	private ProfileConnection profileConnection;
	
	@Autowired
	public StoryService(StoryRepository storyRepository, ProfileMediaService profileMediaService) {
		super();
		this.storyRepository = storyRepository;
		this.profileMediaService = profileMediaService;
	}

	@Override
	public List<Story> addNewStory(StoryDTO storyDTO) {
		
			
	       /* List<TagDTO> tagsDTO = postDTO.getTags();
	        List<Tag> tags = new ArrayList<Tag>();
	        for (TagDTO t : tagsDTO) {
				Tag tag = new Tag(t.getName());
				tags.add(tag);
			}
	        post.setTags(tags);*/
	       
	       List<Story> stories=new ArrayList<>();
	        for(String s:storyDTO.getFileNames()) {
	        	System.out.println(s);
	        	Story story = new Story();
		        String locationFront= storyDTO.getLocation();
		        String parts[] = locationFront.split(",");
		        String country = parts[0];
		        String city = parts[1];
		        String objectName = parts[2];
		        String street = parts[3];
		        
		        List<TagDTO> tagsDTO = storyDTO.getTags();
		        List<Tag> tags = new ArrayList<Tag>();
		        for (TagDTO t : tagsDTO) {
					Tag tag = new Tag(t.getName());
					tags.add(tag);
				}
		        story.setTags(tags);
		        Location location = new Location(city,street,country,objectName);
		        story.setLocation(location);
		        
		        if( storyDTO.getTaggedUsers() != null) {
		            List<ProfileDTO> taggedUsers = storyDTO.getTaggedUsers();
		            List<ProfileMedia> profiles = new ArrayList<ProfileMedia>();
		            for (ProfileDTO profileDTO : taggedUsers) {
		            	ProfileMedia profile = profileMediaService.findByUsername(profileDTO.getUsername());
		            	 profiles.add(profile);
		    		}
		            story.setProfileTags(profiles);
		            }
		        Media media = new Media();
		        media.setFileName(s);
		        List<Media> medias = new ArrayList<Media>();
		        medias.add(media);
	        
	        
	        story.setDescription(storyDTO.getDescription());
	        story.setMedia(medias);
	        
	        story.setDate(LocalDate.now());
	       
	            
	        story.setStartTime(LocalDateTime.now());
	        story.setEndTime(story.getStartTime().plusMinutes(2));//izmijeniti na 24h
	        story.setHighlighted(storyDTO.isHighlighted());
	        story.setCloseFriends(storyDTO.isCloseFriends());
	        
	        profileMediaService.addStoryToProfile(storyDTO, story);
	        
	        Story s1 = storyRepository.save(story);
	        stories.add(story);
	     }
	     profileConnection.storyNotify(storyDTO.getUsername());
	     return stories;
	}

	@Override
	public List<StoryDTO> findMyStories(String username) {
		List<StoryDTO> myStories = new ArrayList<StoryDTO>();
		ProfileMedia existingProfile = profileMediaService.findByUsername(username);
		if(existingProfile == null) {
			throw new IllegalArgumentException("Profile doesn't exist!");
		}
		List<Story> stories = existingProfile.getStories();
		for (Story story : stories) {
			List<ProfileMedia> taggedProfiles = story.getProfileTags();
			List<ProfileDTO> taggedProfilesDTO = new ArrayList<ProfileDTO>();
			if(story.getProfileTags() != null) {
			for (ProfileMedia p : taggedProfiles) {
				taggedProfilesDTO.add(new ProfileDTO(p.getUsername()));
			}
			}
			List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
			List<Tag> tags = story.getTags();
			for (Tag tag : tags) {
				tagsDTO.add(new TagDTO(tag.getName()));
			}
			if(LocalDateTime.now().isBefore(story.getEndTime())) {
				List<Media> medias = story.getMedia();
				for (Media m : medias) {
					LocationDTO locationDTO = new LocationDTO(story.getLocation().getCity(), story.getLocation().getStreet(),story.getLocation().getCountry(),
							story.getLocation().getObjectName());
				
					myStories.add(new StoryDTO(story.getDescription(),username,m.getFileName(),locationDTO,story.isHighlighted(),story.isCloseFriends(),taggedProfilesDTO,tagsDTO));
		}
	}
		
		
		}
		return getImagesFiles(myStories);
	}
	
	 public List<StoryDTO> getImagesFiles(List<StoryDTO> stories) {
		 List<StoryDTO> storiesDto = new ArrayList<>();
	     if (stories != null) {
	    	 String filePath = new File("").getAbsolutePath();
		     filePath = filePath.concat("/" + uploadDir + "/");
	         for (StoryDTO story : stories) {
	        	 storiesDto.add(imageFile(story, filePath));
	        }
	    }
	    return storiesDto;
	    
	 }


	    public StoryDTO imageFile(StoryDTO story, String filePath) {
	    	StoryDTO storiesDto = story;
			List<byte[]> imageBytes = new ArrayList<byte[]>();
			story.setImageBytes(imageBytes);
	        File in = new File(filePath + "/"+story.getFileName());
	        try {
	        	storiesDto.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch(NullPointerException n) {
	            n.printStackTrace();
	        }
	        return storiesDto;
	    }

		@Override
		public List<StoryDTO> findArchiveStories(String username) {
			List<StoryDTO> myStories = new ArrayList<StoryDTO>();
			ProfileMedia existingProfile = profileMediaService.findByUsername(username);
			System.out.println(existingProfile.getUsername());
			if(existingProfile == null) {
				throw new IllegalArgumentException("Profile doesn't exist!");
			}
			List<Story> stories = existingProfile.getStories();
			for (Story story : stories) {
				List<ProfileMedia> taggedProfiles = story.getProfileTags();
				List<ProfileDTO> taggedProfilesDTO = new ArrayList<ProfileDTO>();
				if(story.getProfileTags() != null) {
				for (ProfileMedia p : taggedProfiles) {
					taggedProfilesDTO.add(new ProfileDTO(p.getUsername()));
				}
				}
				List<Media> medias = story.getMedia();
				for (Media m : medias) {
					LocationDTO locationDTO = new LocationDTO(story.getLocation().getCity(), story.getLocation().getStreet(),story.getLocation().getCountry(),
							story.getLocation().getObjectName());
				
					myStories.add(new StoryDTO(story.getDescription(),username,m.getFileName(),locationDTO,story.isHighlighted(),story.isCloseFriends(),taggedProfilesDTO));
			}
			
			}
			
			
			return getImagesFiles(myStories);
		}

		@Override
		public List<StoryDTO> findHighlightedStories(String username) {
			List<StoryDTO> myStories = new ArrayList<StoryDTO>();
			ProfileMedia existingProfile = profileMediaService.findByUsername(username);
			System.out.println(existingProfile.getUsername());
			if(existingProfile == null) {
				throw new IllegalArgumentException("Profile doesn't exist!");
			}
			List<Story> stories = existingProfile.getStories();
			for (Story story : stories) {
				List<ProfileMedia> taggedProfiles = story.getProfileTags();
				List<ProfileDTO> taggedProfilesDTO = new ArrayList<ProfileDTO>();
				if(story.getProfileTags() != null) {
				for (ProfileMedia p : taggedProfiles) {
					taggedProfilesDTO.add(new ProfileDTO(p.getUsername()));
				}
				}
				if(story.isHighlighted()) {
					List<Media> medias = story.getMedia();
					for (Media m : medias) {
						LocationDTO locationDTO = new LocationDTO(story.getLocation().getCity(), story.getLocation().getStreet(),story.getLocation().getCountry(),
								story.getLocation().getObjectName());
					
						myStories.add(new StoryDTO(story.getDescription(),username,m.getFileName(),locationDTO,story.isHighlighted(),story.isCloseFriends(),taggedProfilesDTO));
				}
			}
			}
			return getImagesFiles(myStories);
		}
}
