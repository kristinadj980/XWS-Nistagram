package com.example.searchmicroservice.dto;

import java.time.LocalDateTime;
import java.util.List;


public class PostDTO {
	private String description;
	private String location;
	private List<TagDTO> tags;
	private String username;
	private Long userId;
	private String fileName;
	private LocalDateTime date;
	private LocationDTO locationDTO;
    private List<byte[]> imageBytes;
    private String following;
    private int numberOfLikes;
    private int numberOfDislikes;
	
	public PostDTO() {
		super();
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

	public PostDTO(String description, String username, String fileName, LocationDTO locationDTO, LocalDateTime date,int numberOfLikes,int numberOfDislikes) {
		super();
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.date = date;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
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



	public PostDTO(String description, String fileName, LocationDTO locationDTO, LocalDateTime date,
			int numberOfLikes, int numberOfDislikes) {
		
			this.description=description;
			this.fileName=fileName;
			this.locationDTO=locationDTO;
			this.date=date;
			this.numberOfLikes=numberOfLikes;
			this.numberOfDislikes=numberOfDislikes;
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
}
