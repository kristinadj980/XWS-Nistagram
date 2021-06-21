package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class InappropriateContent {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   @Column(name = "description", nullable = false)
	   private String description;

	   @Column(name = "status")
	   private RequestStatus status;

	   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	   @JoinColumn(name = "post_id", referencedColumnName = "id")
	   public Post post;

	   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	   @JoinColumn(name = "story_id", referencedColumnName = "id")
	   public Story story;
	   
	   @Column(name = "profileToId")
	   private Long profileToId;
	   
	   @Column(name = "profileFromId")
	   private Long profileFromId;

	public InappropriateContent() {
		super();
	}
	
	public InappropriateContent(Long id, String description, RequestStatus status, Post post, Story story,
			Long profileToId, Long profileFromId) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.post = post;
		this.story = story;
		this.profileToId = profileToId;
		this.profileFromId = profileFromId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Long getProfileToId() {
		return profileToId;
	}

	public void setProfileToId(Long profileToId) {
		this.profileToId = profileToId;
	}

	public Long getProfileFromId() {
		return profileFromId;
	}

	public void setProfileFromId(Long profileFromId) {
		this.profileFromId = profileFromId;
	}
}
