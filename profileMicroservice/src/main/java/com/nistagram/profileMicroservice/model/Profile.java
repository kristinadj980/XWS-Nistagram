package com.nistagram.profileMicroservice.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Profile")
public class Profile extends Person{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@Column(name = "website", nullable = false)
	private String website;

	@Column(name = "biography", nullable = false)
	private String biography;

	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Notification> notifications;

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

	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FriendRequest> friendRequests;

	@Enumerated(EnumType.ORDINAL)
	private RequestStatus agentRequestStatus;
	
	public Profile() {
		super();
	}

	public Profile(Long id, ProfileStatus profileStatus, List<Post> favourites, List<Profile> closeFriends,
			String website, String biography, List<Notification> notifications, List<Profile> followers,
			List<Profile> mutedFriends, List<Profile> blockedUsers, List<FriendRequest> friendRequests,
			RequestStatus agentRequestStatus) {
		super();
		this.id = id;
		this.profileStatus = profileStatus;
		this.favourites = favourites;
		this.closeFriends = closeFriends;
		this.website = website;
		this.biography = biography;
		this.notifications = notifications;
		this.followers = followers;
		this.mutedFriends = mutedFriends;
		this.blockedUsers = blockedUsers;
		this.friendRequests = friendRequests;
		this.agentRequestStatus = agentRequestStatus;
	}

	
	public Profile(Long id, String username, String name, String surname, String email, String password,
			String phoneNumber, String role, LocalDate birthDate, Gender gender, List<Authority> authorities) {
		super(id, username, name, surname, email, password, phoneNumber, role, birthDate, gender, authorities);
		// TODO Auto-generated constructor stub
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<FriendRequest> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(List<FriendRequest> friendRequests) {
		this.friendRequests = friendRequests;
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
	
	public RequestStatus getAgentRequestStatus() {
		return agentRequestStatus;
	}
	
	public void setAgentRequestStatus(RequestStatus agentRequestStatus) {
		this.agentRequestStatus = agentRequestStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
