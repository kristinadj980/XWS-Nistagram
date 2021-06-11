package com.example.mediamicroservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	public ProfileMedia registredUserProfile;
	   
	   
	public Comment() {
		super();
	}

	public Comment(Long id, String description, ProfileMedia registredUserProfile) {
		super();
		this.id = id;
		this.description = description;
		this.registredUserProfile = registredUserProfile;
	}

	public Comment(String description, ProfileMedia registredUserProfile) {
		super();
		this.description = description;
		this.registredUserProfile = registredUserProfile;
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

	public ProfileMedia getRegistredUserProfile() {
		return registredUserProfile;
	}

	public void setRegistredUserProfile(ProfileMedia registredUserProfile) {
		this.registredUserProfile = registredUserProfile;
	}
}
