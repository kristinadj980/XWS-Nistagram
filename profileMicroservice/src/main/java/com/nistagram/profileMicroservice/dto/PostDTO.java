package com.nistagram.profileMicroservice.dto;

import java.util.List;

public class PostDTO {
	private String description;
	private String location;
	//private List<TagDTO> tags;
	private String username;
	private Long userId;
	private String fileName;
	private LocationDTO locationDTO;
	
	public PostDTO() {
		super();
	}

	public PostDTO(String description, String location, String username,
			Long userId, String fileName) {
		super();
		this.description = description;
		this.location = location;
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

	public PostDTO(String description, String username, String fileName, LocationDTO locationDTO) {
		super();
		this.description = description;
		this.username = username;
		this.fileName = fileName;
		this.locationDTO = locationDTO;
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

}
