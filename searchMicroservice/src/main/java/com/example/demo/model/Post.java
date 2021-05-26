package com.example.demo.model;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "post_likes",
	joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "likes_id", referencedColumnName = "id"))
	private	List<Profile> likes;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "post_dislikes",
	joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "dislikes_id", referencedColumnName = "id"))
	private List<Profile> dislikes;

	@Column(name = "date", nullable = false)
	private LocalDate date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	   public Location location;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "post_tags",
	joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private	List<Tag> tags;
	
	
	public Post(String description, List<Profile> likes, List<Profile> dislikes, LocalDate date, Location location) {
		super();
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.date = date;
		this.location = location;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Profile> getLikes() {
		return likes;
	}


	public void setLikes(List<Profile> likes) {
		this.likes = likes;
	}


	public List<Profile> getDislikes() {
		return dislikes;
	}


	public void setDislikes(List<Profile> dislikes) {
		this.dislikes = dislikes;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}