package com.nistagram.profileMicroservice.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Profile")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profile extends Person{

	@Enumerated(EnumType.ORDINAL)
	private ProfileStatus profileStatus;  

	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinTable(name = "profile_favourites",
	//joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	//inverseJoinColumns = @JoinColumn(name = "favourites_id", referencedColumnName = "id"))
	//private List<Post> favourites;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_closeFriends",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "closeFriends_id", referencedColumnName = "id"))
	private List<Profile> closeFriends;  

	@Column(name = "website")  
	private String website;

	@Column(name = "biography")  
	private String biography;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_notifications",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id"))
	private List<Notification> notifications;  
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_friend_notifications",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "friendNotifications_id", referencedColumnName = "id"))
	private List<FriendNotifications> friendNotifications;  

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_followers",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "followers_id", referencedColumnName = "id"))
	private List<Profile> followers; 
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "profile_following",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "followers_id", referencedColumnName = "id"))
	private List<Profile> following; 
	
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
	@JoinTable(name = "profile_friend_requests",
	joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "friend_requests_id", referencedColumnName = "id"))
	private List<FriendRequest> friendRequests;  

	@Enumerated(EnumType.ORDINAL)
	private RequestStatus agentRequestStatus;  
	
	@Column
	private Boolean allowedTags; 
	
	@Column
	private Boolean allowedMessages; 
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private VerificationRequest verificationRequest;
	
	public Profile() {
		super();
	}


	public Profile(ProfileStatus profileStatus, List<Profile> closeFriends, String website, String biography,
			List<Notification> notifications, List<FriendNotifications> friendNotifications, List<Profile> followers,
			List<Profile> following, List<Profile> mutedFriends, List<Profile> blockedUsers,
			List<FriendRequest> friendRequests, RequestStatus agentRequestStatus, Boolean allowedTags,
			Boolean allowedMessages, VerificationRequest verificationRequest) {
		super();
		this.profileStatus = profileStatus;
		this.closeFriends = closeFriends;
		this.website = website;
		this.biography = biography;
		this.notifications = notifications;
		this.friendNotifications = friendNotifications;
		this.followers = followers;
		this.following = following;
		this.mutedFriends = mutedFriends;
		this.blockedUsers = blockedUsers;
		this.friendRequests = friendRequests;
		this.agentRequestStatus = agentRequestStatus;
		this.allowedTags = allowedTags;
		this.allowedMessages = allowedMessages;
		this.verificationRequest = verificationRequest;
	}


	public Profile(Long id, String username, String name, String surname, String email, String password,
			String phoneNumber, String role, LocalDate birthDate, Gender gender, List<Authority> authorities,
			boolean enabled,ProfileStatus profileStatus, List<Profile> closeFriends, String website, String biography,
			List<Notification> notifications, List<Profile> followers, List<Profile> following, List<Profile> mutedFriends,
			List<Profile> blockedUsers, List<FriendRequest> friendRequests, RequestStatus agentRequestStatus,
			Boolean allowedTags, Boolean allowedMessages, VerificationRequest verificationRequest) {
		super(id, username, name, surname, email, password, phoneNumber, role, birthDate, gender, authorities, enabled);
		// TODO Auto-generated constructor stub
		this.profileStatus = profileStatus;
		this.closeFriends = closeFriends;
		this.website = website;
		this.biography = biography;
		this.notifications = notifications;
		this.followers = followers;
		this.following = following;
		this.mutedFriends = mutedFriends;
		this.blockedUsers = blockedUsers;
		this.friendRequests = friendRequests;
		this.agentRequestStatus = agentRequestStatus;
		this.allowedTags = allowedTags;
		this.allowedMessages = allowedMessages;
		this.verificationRequest = verificationRequest;
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
	
	public List<Profile> getFollowing() {
		return following;
	}

	public void setFollowing(List<Profile> following) {
		this.following = following;
	}

	public Boolean getAllowedTags() {
		return allowedTags;
	}

	public void setAllowedTags(Boolean allowedTags) {
		this.allowedTags = allowedTags;
	}

	public Boolean getAllowedMessages() {
		return allowedMessages;
	}

	public void setAllowedMessages(Boolean allowedMessages) {
		this.allowedMessages = allowedMessages;
	}

	public VerificationRequest getVerificationRequest() {
		return verificationRequest;
	}

	public void setVerificationRequest(VerificationRequest verificationRequest) {
		this.verificationRequest = verificationRequest;
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


	public List<FriendNotifications> getFriendNotifications() {
		return friendNotifications;
	}


	public void setFriendNotifications(List<FriendNotifications> friendNotifications) {
		this.friendNotifications = friendNotifications;
	}
	
	
	
	/*@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUsername();
	}*/

}
