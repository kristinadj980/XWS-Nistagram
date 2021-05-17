package com.example.mediamicroservice.model;

public class InappropriateContent {
	   
	   private String description;
	   private RequestStatus status;
	   
	   public Post[] post;
	   public Story[] story;
	   
	   public InappropriateContent(String description, RequestStatus status, Post[] post, Story[] story) {
			super();
			this.description = description;
			this.status = status;
			this.post = post;
			this.story = story;
		}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Post[] getPost() {
		return post;
	}

	public void setPost(Post[] post) {
		this.post = post;
	}

	public Story[] getStory() {
		return story;
	}

	public void setStory(Story[] story) {
		this.story = story;
	}
	   
	   
	   
}
