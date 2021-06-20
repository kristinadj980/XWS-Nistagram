package com.nistagram.profileMicroservice.dto;


public class FriendNotificationsDTO {
	
	private Long id;
	private Boolean message;
	private Boolean post;
	private Boolean story;
	private Boolean comment;
	
	
	public FriendNotificationsDTO() {
		super();
	}

	public FriendNotificationsDTO(Long id, Boolean message, Boolean post, Boolean story, Boolean comment) {
		super();
		this.id = id;
		this.message = message;
		this.post = post;
		this.story = story;
		this.comment = comment;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getMessage() {
		return message;
	}


	public void setMessage(Boolean message) {
		this.message = message;
	}


	public Boolean getPost() {
		return post;
	}


	public void setPost(Boolean post) {
		this.post = post;
	}


	public Boolean getStory() {
		return story;
	}


	public void setStory(Boolean story) {
		this.story = story;
	}


	public Boolean getComment() {
		return comment;
	}


	public void setComment(Boolean comment) {
		this.comment = comment;
	}
	
	
}
