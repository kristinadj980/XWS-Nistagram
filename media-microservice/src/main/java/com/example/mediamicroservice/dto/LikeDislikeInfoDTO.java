package com.example.mediamicroservice.dto;

public class LikeDislikeInfoDTO {
	private String username;
	private String fileName;
	private String myProfile;

	public LikeDislikeInfoDTO() {
		super();
	}
	
	public LikeDislikeInfoDTO(String username, String fileName, String myProfile) {
		super();
		this.username = username;
		this.fileName = fileName;
		this.myProfile = myProfile;
	}

	public LikeDislikeInfoDTO(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}
}
