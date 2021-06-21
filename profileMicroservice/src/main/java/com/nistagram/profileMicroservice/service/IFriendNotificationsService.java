package com.nistagram.profileMicroservice.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.nistagram.profileMicroservice.dto.FriendNotificationsDTO;

public interface IFriendNotificationsService {

	FriendNotificationsDTO getfriendNotifications(String username);
	FriendNotificationsDTO changeNotificationStatus( FriendNotificationsDTO friendNotificationsDTO);
}
