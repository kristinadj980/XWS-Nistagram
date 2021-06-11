package com.nistagram.profileMicroservice.dto;

import java.util.List;

public class FollowingDTO {
	
	private String following;

	public FollowingDTO() {
		super();
	}

	public FollowingDTO(String following) {
		super();
		this.following = following;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}
	
	

}
