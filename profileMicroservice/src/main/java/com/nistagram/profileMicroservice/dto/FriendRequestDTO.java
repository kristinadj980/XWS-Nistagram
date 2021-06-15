package com.nistagram.profileMicroservice.dto;

import com.nistagram.profileMicroservice.model.FriendRequestStatus;

public class FriendRequestDTO {
	
	private String userReceiver;
	private Long requestId;
	private FriendRequestStatus requestStatus;
	
	
	public FriendRequestDTO() {
		super();
	}

	
	
	public FriendRequestDTO(String userReceiver, Long requestId, FriendRequestStatus requestStatus) {
		super();
		this.userReceiver = userReceiver;
		this.requestId = requestId;
		this.requestStatus = requestStatus;
	}


	
	public FriendRequestDTO(String userReceiver, Long requestId) {
		super();
		this.userReceiver = userReceiver;
		this.requestId = requestId;
	}



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

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public FriendRequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(FriendRequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	

}
