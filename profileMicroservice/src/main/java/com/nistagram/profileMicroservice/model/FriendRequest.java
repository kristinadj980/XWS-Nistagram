package com.nistagram.profileMicroservice.model;

import javax.persistence.*;

public class FriendRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Profile profile;

	@Enumerated(EnumType.ORDINAL)
	private FriendRequestStatus friendRequestStatus;
	
	public FriendRequest() {
		super();
	}

	public FriendRequest(Long id, Profile profile, FriendRequestStatus friendRequestStatus) {
		this.id = id;
		this.profile = profile;
		this.friendRequestStatus = friendRequestStatus;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public FriendRequestStatus getFriendRequestStatus() {
		return friendRequestStatus;
	}

	public void setFriendRequestStatus(FriendRequestStatus friendRequestStatus) {
		this.friendRequestStatus = friendRequestStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
