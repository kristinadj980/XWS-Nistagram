package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.dto.FriendNotificationsDTO;

public interface IFriendNotificationsService {

	FriendNotificationsDTO getfriendNotifications(String username);
}
