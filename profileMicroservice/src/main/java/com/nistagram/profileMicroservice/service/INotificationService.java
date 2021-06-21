package com.nistagram.profileMicroservice.service;

import java.util.List;

import com.nistagram.profileMicroservice.dto.NotificationDTO;

public interface INotificationService {
	
	void postNotify(String username);
	List<NotificationDTO> getNotifications();
}
