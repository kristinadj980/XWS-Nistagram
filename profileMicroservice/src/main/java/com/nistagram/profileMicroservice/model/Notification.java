package com.nistagram.profileMicroservice.model;

public class Notification {
	private Long id;
	private Boolean status;
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
