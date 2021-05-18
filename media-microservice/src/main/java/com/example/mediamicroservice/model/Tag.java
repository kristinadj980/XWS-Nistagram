package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {


	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(name = "name",nullable = false)
	  private String name;
	   
	   public List<Post> post;
	   public List<Story> story;
	   
	   
	   
	   public Tag(String name, List<Post> post, List<Story> story) {
			super();
			this.name = name;
			this.post = post;
			this.story = story;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
