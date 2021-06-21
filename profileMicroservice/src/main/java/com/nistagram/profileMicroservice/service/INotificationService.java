package com.nistagram.profileMicroservice.service;

import java.util.List;

import com.nistagram.profileMicroservice.dto.CommentNotifyDTO;
import com.nistagram.profileMicroservice.dto.NotificationDTO;

public interface INotificationService {
	
	void postNotify(String username);
	void storyNotify(String username);
	void commentNotify(CommentNotifyDTO commentNotifyDTO);
	List<NotificationDTO> getNotifications();
	
}
