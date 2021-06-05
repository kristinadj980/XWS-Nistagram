package com.example.mediamicroservice.dto;

import java.util.List;

public class PostDTO {
	
	private String description;
	private LocationDTO location;
	private List<TagDTO> tags;
	private List<MediaDTO> medias;
	private String username;
	private Long userId;
	
	public PostDTO() {
		super();
	}

	public PostDTO(String description, LocationDTO location, List<TagDTO> tags, List<MediaDTO> medias, String username,
			Long userId) {
		super();
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.medias = medias;
		this.username = username;
		this.userId = userId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public List<MediaDTO> getMedias() {
		return medias;
	}

	public void setMedias(List<MediaDTO> medias) {
		this.medias = medias;
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
}
