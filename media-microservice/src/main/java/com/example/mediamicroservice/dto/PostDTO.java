package com.example.mediamicroservice.dto;

import java.util.List;

public class PostDTO {
	
	private String description;
	private String location;
	private List<TagDTO> tags;
	private String username;
	private Long userId;
	private String fileName;
	
	public PostDTO() {
		super();
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

}
