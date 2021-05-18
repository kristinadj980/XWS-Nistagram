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

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "tag_posts",
			joinColumns = { @JoinColumn(name = "id") },
			inverseJoinColumns = { @JoinColumn(name = "id") }
	)
	public List<Post> posts;


	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "tag_stories",
			joinColumns = { @JoinColumn(name = "id") },
			inverseJoinColumns = { @JoinColumn(name = "id") }
	)
	public List<Story> stories;
	   
	   
	   
	   public Tag(String name, List<Post> post, List<Story> story) {
			super();
			this.name = name;
			this.posts = post;
			this.stories = story;
	}

	public Tag() {

	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Post> getPost() {
		return posts;
	}



	public void setPost(List<Post> post) {
		this.posts = post;
	}



	public List<Story> getStory() {
		return stories;
	}



	public void setStory(List<Story> story) {
		this.stories = story;
	}
	   
	   
}
