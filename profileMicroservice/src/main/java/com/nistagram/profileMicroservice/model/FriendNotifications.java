package com.nistagram.profileMicroservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FriendNotifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "message", nullable = false)
	private Boolean message;
	
	@Column(name = "post", nullable = false)
	private Boolean post;
	
	@Column(name = "story", nullable = false)
	private Boolean story;
	
	@Column(name = "comment", nullable = false)
	private Boolean comment;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;
	
	
	public FriendNotifications() {
		super();
	}
	
	public FriendNotifications(Long id, Boolean message, Boolean post, Boolean story, Boolean comment, Profile profile) {
		super();
		this.id = id;
		this.message = message;
		this.post = post;
		this.story = story;
		this.comment = comment;
		this.profile = profile;
	}

	public FriendNotifications(Boolean message, Boolean post, Boolean story, Boolean comment, Profile profile) {
		super();
		this.message = message;
		this.post = post;
		this.story = story;
		this.comment = comment;
		this.profile = profile;
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
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
}
