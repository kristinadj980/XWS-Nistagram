package com.example.mediamicroservice.dto;

public class ProfileDTO {
	private String username;
	private String name;
	private String surname;
	private String email;
	private String currentUsername;
	private String newUsername;
	
	
	public ProfileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProfileDTO(String username, String name, String surname, String email, String currentUsername,
			String newUsername) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.currentUsername = currentUsername;
		this.newUsername = newUsername;
	}

	public ProfileDTO(String username, String name, String surname, String email) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	
	public ProfileDTO(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	
}
