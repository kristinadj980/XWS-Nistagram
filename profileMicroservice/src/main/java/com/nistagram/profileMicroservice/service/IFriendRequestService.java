package com.nistagram.profileMicroservice.service;

import java.util.List;

import com.nistagram.profileMicroservice.dto.FriendRequestDTO;

public interface IFriendRequestService {
	
	String newFriendRequest(FriendRequestDTO friendRequestDTO);
	List<FriendRequestDTO> getMyRequests();
	String setRequestResponse(FriendRequestDTO friendRequestDTO);
	void cancelRequest(FriendRequestDTO friendRequestDTO);
	void unfollowFriend(FriendRequestDTO friendRequestDTO);
	void removeFollowera(FriendRequestDTO friendRequestDTO);
}
