package com.example.mediamicroservice.dto;

import java.time.LocalDateTime;
import java.util.List;


import com.example.mediamicroservice.model.Tag;



import lombok.Data;

@Data
public class PostDTO {
	
	private String description;
	private String location;
	private List<TagDTO> tags;
	private String username;
	private Long userId;
	private String fileName;
	private List<String> fileNames;
	private LocalDateTime date;
	private LocationDTO locationDTO;
    private List<byte[]> imageBytes;
    private String following;
    private int numberOfLikes;
    private int numberOfDislikes;
    private int numberOfComments;
    private Long id;
	private List<ImageDTO> images;
	private List<ProfileDTO> taggedUsers;
    
	public PostDTO() {
		super();
	}


	public PostDTO(String description, String location, List<TagDTO> tags, String username, Long userId,
			String fileName, List<String> fileNames, LocalDateTime date, LocationDTO locationDTO,
			List<byte[]> imageBytes, String following, int numberOfLikes, int numberOfDislikes, int numberOfComments,
			Long id) {
		super();
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.username = username;
		this.userId = userId;
		this.fileName = fileName;
		this.fileNames = fileNames;
		this.date = date;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
		this.following = following;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.numberOfComments = numberOfComments;
		this.id = id;
		
	}



	public PostDTO(String description, String location, List<TagDTO> tags, String username, Long userId,
			String fileName, LocationDTO locationDTO, List<byte[]> imageBytes) {
		super();
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.username = username;
		this.userId = userId;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
	}

	public PostDTO(LocalDateTime date) {
		super();
		this.date = date;	

	}

	public PostDTO(String description, String location, List<TagDTO> tags, String username,
			Long userId, String fileName) {
		super();
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.username = username;
		this.userId = userId;
		this.fileName = fileName;
	}

	public PostDTO(String description, String location, String username, String fileName) {
		super();
		this.description = description;
		this.location = location;
		this.username = username;
		this.fileName = fileName;
	}

	public PostDTO(String description, String username, String fileName, LocationDTO locationDTO, LocalDateTime date,int numberOfLikes,int numberOfDislikes,
			int numberOfComments,List<TagDTO> tags,Long id) {
		super();
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.date = date;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.numberOfComments = numberOfComments;
		this.tags = tags;
		this.id = id;
		
	}
	
	public PostDTO(String description, String username, String fileName, LocationDTO locationDTO, List<byte[]> imageBytes) {
		super();
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
	}

	public PostDTO(String following) {
		super();
		this.following = following;
	}

	
	public PostDTO(String description, String username, List<String> fileNames, LocationDTO locationDTO, LocalDateTime date,int numberOfLikes,int numberOfDislikes,
			int numberOfComments,List<TagDTO> tags,Long id,List<ProfileDTO> taggedUsers) {
		super();
		this.description = description;
		this.username = username;
		this.fileNames = fileNames;
		this.locationDTO = locationDTO;
		this.date = date;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.numberOfComments = numberOfComments;
		this.tags = tags;
		this.id = id;
		this.taggedUsers = taggedUsers;
	}


	public PostDTO(String description, String fileName, LocationDTO locationDTO, LocalDateTime date,
			int numberOfLikes, int numberOfDislikes) {
		
			this.description=description;
			this.fileName=fileName;
			this.locationDTO=locationDTO;
			this.date=date;
			this.numberOfLikes=numberOfLikes;
			this.numberOfDislikes=numberOfDislikes;
	}



	public PostDTO(String description, List<TagDTO> tagsDTO, String username, String fileName,
			LocationDTO locationDTO, LocalDateTime date, int numberOfLikes, int numberOfDislikes) {
		
		this.description=description;
		this.fileName=fileName;
		this.username=username;
		this.locationDTO=locationDTO;
		this.date=date;
		this.numberOfLikes=numberOfLikes;
		this.numberOfDislikes=numberOfDislikes;
		this.tags=tagsDTO;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFileNames() {
		return fileNames;
	}



	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocationDTO getLocationDTO() {
		return locationDTO;
	}

	public void setLocationDTO(LocationDTO locationDTO) {
		this.locationDTO = locationDTO;
	}

	public List<byte[]> getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(List<byte[]> imageBytes) {
		this.imageBytes = imageBytes;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getFollowing() {
		return following;
	}
	
	public void setFollowing(String following) {
		this.following = following;
	}
	
	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public int getNumberOfComments() {
		return numberOfComments;
	}
	
	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProfileDTO> getTaggedUsers() {
		return taggedUsers;
	}

	public void setTaggedUsers(List<ProfileDTO> taggedUsers) {
		this.taggedUsers = taggedUsers;
	}
}
