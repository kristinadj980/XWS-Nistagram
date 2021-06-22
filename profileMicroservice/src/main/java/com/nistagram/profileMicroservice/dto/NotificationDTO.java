package com.nistagram.profileMicroservice.dto;

import java.time.LocalDate;

import com.nistagram.profileMicroservice.model.NotificationDescription;

public class NotificationDTO {

	private Long id;
	private NotificationDescription notificationDescription;
	private String description;
	private LocalDate date;
	private String username;
	
	public NotificationDTO() {
		super();
	}

	

	public NotificationDTO(Long id, NotificationDescription notificationDescription, String description, LocalDate date,
			String username) {
		super();
		this.id = id;
		this.notificationDescription = notificationDescription;
		this.description = description;
		this.date = date;
		this.username = username;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotificationDescription getNotificationDescription() {
		return notificationDescription;
	}

	public void setNotificationDescription(NotificationDescription notificationDescription) {
		this.notificationDescription = notificationDescription;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
