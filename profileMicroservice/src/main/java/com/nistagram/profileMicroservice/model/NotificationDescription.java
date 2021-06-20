package com.nistagram.profileMicroservice.model;

public enum NotificationDescription {

	MESSAGE("sent you a message"),
	POST("shared new post"),
	STORY("shared new story"),
	COMMENT("mentioned you in a comment");
	
	private String notificationDescription;
	
	private NotificationDescription(String n) {
		notificationDescription = n;
	}

	public String getNotificationDescription() {
		return notificationDescription;
	}

	
}
