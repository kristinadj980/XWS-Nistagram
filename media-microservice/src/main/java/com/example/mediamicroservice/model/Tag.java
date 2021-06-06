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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tag_posts",
	joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
	public List<Post> posts;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tag_posts",
	joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"))
	public List<Story> stories;
	   

	public Tag() {

	}   
	   
	public Tag(String name, List<Post> post, List<Story> story) {
		super();
		this.name = name;
		this.posts = post;
		this.stories = story;
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
