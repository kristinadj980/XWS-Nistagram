package com.nistagram.profileMicroservice.dto;

public class FriendRequestDTO {
	
	private String userReceiver;

	public FriendRequestDTO(String userReceiver) {
		super();
		this.userReceiver = userReceiver;
	}

	public String getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(String userReceiver) {
		this.userReceiver = userReceiver;
	}
	

}
