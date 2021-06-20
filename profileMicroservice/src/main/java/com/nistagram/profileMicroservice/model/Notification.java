package com.nistagram.profileMicroservice.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private NotificationDescription notificationDescription;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;
	
	
	public Notification() {
		super();
	}


	public Notification(Long id, NotificationDescription notificationDescription, LocalDate date, Profile profile) {
		super();
		this.id = id;
		this.notificationDescription = notificationDescription;
		this.date = date;
		this.profile = profile;
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


	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
}
