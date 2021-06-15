package com.example.mediamicroservice.dto;

public class FavouritesDTO {
	
	private String fileName;
	private String myProfile;
	
	public FavouritesDTO() {
		super();
	}

	public FavouritesDTO(String fileName, String myProfile) {
		super();
		this.fileName = fileName;
		this.myProfile = myProfile;
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
