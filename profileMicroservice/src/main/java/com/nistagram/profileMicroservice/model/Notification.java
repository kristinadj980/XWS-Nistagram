package com.nistagram.profileMicroservice.model;

import javax.persistence.*;

public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status", nullable = false)
	private Boolean status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Profile profile;
	
	public Notification() {
		super();
	}

	public Notification(Long id, Boolean status, Profile profile) {
		this.id = id;
		this.status = status;
		this.profile = profile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
