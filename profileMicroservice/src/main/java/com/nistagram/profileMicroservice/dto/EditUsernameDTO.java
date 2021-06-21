package com.nistagram.profileMicroservice.dto;

public class EditUsernameDTO {
	
	private String currentUsername;
	private String newUsername;
	
	public EditUsernameDTO() {
		super();
	}

	public EditUsernameDTO(String currentUsername, String newUsername) {
		super();
		this.currentUsername = currentUsername;
		this.newUsername = newUsername;
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
