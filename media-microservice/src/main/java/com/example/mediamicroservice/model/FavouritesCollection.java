package com.example.mediamicroservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class FavouritesCollection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "collection_posts",
	joinColumns = @JoinColumn(name = "favourites_collection_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "posts_id", referencedColumnName = "id"))
	private List<Post> posts;
	
	public FavouritesCollection() {
		super();
	}

	public FavouritesCollection(Long id, String name, List<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.posts = posts;
	}

	public FavouritesCollection(String name, List<Post> posts) {
		super();
		this.name = name;
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
