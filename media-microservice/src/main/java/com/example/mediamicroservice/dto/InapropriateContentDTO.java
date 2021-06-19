package com.example.mediamicroservice.dto;

public class InapropriateContentDTO {
	private String usernameTo;
	private String usernameFrom;
	private Long postId;
	private String description;
	
	public InapropriateContentDTO() {
		super();
	}

	public InapropriateContentDTO(String usernameTo, String usernameFrom, Long postId, String description) {
		super();
		this.usernameTo = usernameTo;
		this.usernameFrom = usernameFrom;
		this.postId = postId;
		this.description = description;
	}

	public String getUsernameTo() {
		return usernameTo;
	}

	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}

	public String getUsernameFrom() {
		return usernameFrom;
	}

	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
