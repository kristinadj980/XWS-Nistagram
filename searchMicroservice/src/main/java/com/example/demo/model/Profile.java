package com.example.demo.model;

import java.util.List;

public class Profile extends Person {
	
	private ProfileStatus profileStatus;;
	private String website;
	private String biography;
	private List<Profile> followers;
	
	public Profile() {
		
	}
	
	public Profile(ProfileStatus profileStatus, String website, String biography, List<Profile> followers) {
		super();
		this.profileStatus = profileStatus;
		this.website = website;
		this.biography = biography;
		this.followers = followers;
	}
	
	public ProfileStatus getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(ProfileStatus profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public List<Profile> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Profile> followers) {
		this.followers = followers;
	}
	
	
	
}