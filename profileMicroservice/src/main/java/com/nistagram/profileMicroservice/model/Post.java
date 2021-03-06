package com.nistagram.profileMicroservice.model;

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
	private List<Profile> likes;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "post_dislikes",
	joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "dislikes_id", referencedColumnName = "id"))
    private List<Profile> dislikes;

	@Column(name = "comment", nullable = false)
	private String comment;

	@Column(name = "date", nullable = false)
	private LocalDate date;
	
	public Post() {
		super();
	}

	public Post(Long id, String description, List<Profile> likes, List<Profile> dislikes, String comment, LocalDate date) {
		this.id = id;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.comment = comment;
		this.date = date;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
