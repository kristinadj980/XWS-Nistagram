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

	   @Column(name = "status", nullable = false)
	   private RequestStatus status;

	   @ManyToMany(targetEntity = Post.class,  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   public List<Post> post;

	   @ManyToMany(targetEntity = Story.class,  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   public List<Story> story;
	   
	   public InappropriateContent(String description, RequestStatus status, List<Post> post, List<Story> story) {
			super();
			this.description = description;
			this.status = status;
			this.post = post;
			this.story = story;
		}

	public InappropriateContent() {

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

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Story> getStory() {
		return story;
	}

	public void setStory(List<Story> story) {
		this.story = story;
	}
	   
	   
	   
}
