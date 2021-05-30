package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Profile")
public class Profile extends Person {

	@Column(name = "website", nullable = false)
	private String website;

	@Column(name = "biography", nullable = false)
	private String biography;
	
	@Enumerated(EnumType.ORDINAL)
	private ProfileStatus profileStatus;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_favourites",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "favourites_id", referencedColumnName = "id"))
	private List<Post> favourites;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_closeFriends",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "closeFriends_id", referencedColumnName = "id"))
	private List<Profile> closeFriends;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_followers",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "followers_id", referencedColumnName = "id"))
	private List<Profile> followers;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_mutedFriends",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "mutedFriends_id", referencedColumnName = "id"))
	private List<Profile> mutedFriends;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_blockedUsers",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "blockedUsers_id", referencedColumnName = "id"))
	private List<Profile> blockedUsers;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_stories",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "stories_id", referencedColumnName = "id"))
	public List<Story> stories;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_posts",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "posts_id", referencedColumnName = "id"))
	public List<Post> posts;
	   
	public Profile() {

	}	

	public Profile(Long id, String username, String name, String surname, String email, String password, String role,
			String phoneNumber, LocalDate birthDate, Gender gender) {
		super(id, username, name, surname, email, password, role, phoneNumber, birthDate, gender);
		// TODO Auto-generated constructor stub
	}


	public ProfileStatus getProfileStatus() {
		return profileStatus;
	}


	public void setProfileStatus(ProfileStatus profileStatus) {
		this.profileStatus = profileStatus;
	}


	public List<Post> getFavourites() {
		return favourites;
	}


	public void setFavourites(List<Post> favourites) {
		this.favourites = favourites;
	}


	public List<Profile> getCloseFriends() {
		return closeFriends;
	}


	public void setCloseFriends(List<Profile> closeFriends) {
		this.closeFriends = closeFriends;
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


	public List<Profile> getMutedFriends() {
		return mutedFriends;
	}


	public void setMutedFriends(List<Profile> mutedFriends) {
		this.mutedFriends = mutedFriends;
	}


	public List<Profile> getBlockedUsers() {
		return blockedUsers;
	}


	public void setBlockedUsers(List<Profile> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}


	public List<Story> getStory() {
		return stories;
	}


	public void setStory(List<Story> story) {
		this.stories = story;
	}


	public List<Post> getPost() {
		return posts;
	}


	public void setPost(List<Post> post) {
		this.posts = post;
	}

   
}