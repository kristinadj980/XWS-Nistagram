package com.nistagram.profileMicroservice.dto;

public class ProfileDTO {
	private String username;

	public ProfileDTO() {
		super();
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
	
	

}
