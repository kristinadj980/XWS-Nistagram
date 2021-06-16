package com.example.mediamicroservice.dto;

import java.util.List;

import com.netflix.client.ssl.ClientSslSocketFactoryException;

public class StoryDTO {
	private String location;
	private String description;
	private List<TagDTO> tags;
	private String username;
	private Long userId;
	private String fileName;
	private boolean highlighted;
	 private String following;
	private LocationDTO locationDTO;
    private List<byte[]> imageBytes;
    private boolean closeFriends;
	

	public StoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StoryDTO(String location, String description, List<TagDTO> tags, String username, Long userId,
			String fileName, boolean highlighted, String following, LocationDTO locationDTO, List<byte[]> imageBytes,
			boolean closeFriends) {
		super();
		this.location = location;
		this.description = description;
		this.tags = tags;
		this.username = username;
		this.userId = userId;
		this.fileName = fileName;
		this.highlighted = highlighted;
		this.following = following;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
		this.closeFriends = closeFriends;
	}
	
	public StoryDTO(String description, String username, String fileName, LocationDTO locationDTO,boolean highlighted,boolean closeFriends) {
		super();
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.highlighted=highlighted;
		this.closeFriends=closeFriends;
	}

	public StoryDTO(String location, String description, String username, String fileName, LocationDTO locationDTO,
			List<byte[]> imageBytes,boolean highlighted,boolean closeFriends) {
		super();
		this.location = location;
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
		this.highlighted=highlighted;
		this.closeFriends=closeFriends;
	}

	public boolean isCloseFriends() {
		return closeFriends;
	}

	public void setCloseFriends(boolean closeFriends) {
		this.closeFriends = closeFriends;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public StoryDTO(String location,String description, List<TagDTO> tags, String username, Long userId, String fileName,
			LocationDTO locationDTO, List<byte[]> imageBytes) {
		super();
		this.location = location;
		this.description=description;
		this.tags = tags;
		this.username = username;
		this.userId = userId;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
		this.imageBytes = imageBytes;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}
	
    
}
