package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ProfileMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profileMedia_favourites",
	joinColumns = @JoinColumn(name = "profileMedia_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "favourites_id", referencedColumnName = "id"))
	private List<Post> favourites;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profileMedia_stories",
	joinColumns = @JoinColumn(name = "profileMedia_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "stories_id", referencedColumnName = "id"))
	public List<Story> stories;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profileMedia_posts",
	joinColumns = @JoinColumn(name = "profileMedia_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "posts_id", referencedColumnName = "id"))
	public List<Post> posts;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profileMedia_favouritesCollections",
	joinColumns = @JoinColumn(name = "profileMedia_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "favourites_collections_id", referencedColumnName = "id"))
	private List<FavouritesCollection> favouritesCollections;
	   
	public ProfileMedia() {

	}

	public ProfileMedia(Long id, String username, List<Post> favourites, List<Story> stories, List<Post> posts,
			List<FavouritesCollection> favouritesCollections) {
		super();
		this.id = id;
		this.username = username;
		this.favourites = favourites;
		this.stories = stories;
		this.posts = posts;
		this.favouritesCollections = favouritesCollections;
	}

	public ProfileMedia(String username, List<Post> posts) {
		super();
		this.username = username;
		this.posts = posts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Post> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Post> favourites) {
		this.favourites = favourites;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FavouritesCollection> getFavouritesCollections() {
		return favouritesCollections;
	}

	public void setFavouritesCollections(List<FavouritesCollection> favouritesCollections) {
		this.favouritesCollections = favouritesCollections;
	}	
	
	

}