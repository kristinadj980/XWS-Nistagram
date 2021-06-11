package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.dto.FriendRequestDTO;

public interface IFriendRequestService {
	
	void newFriendRequest(FriendRequestDTO friendRequestDTO);
}
