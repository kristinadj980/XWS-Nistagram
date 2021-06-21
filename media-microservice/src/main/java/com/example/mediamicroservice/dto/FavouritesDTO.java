package com.example.mediamicroservice.dto;

import java.util.List;

public class FavouritesDTO {
	private Long postId;
	private List<String> fileNames;
	private String myProfile;
	
	public FavouritesDTO() {
		super();
	}

	public FavouritesDTO(Long postId, List<String> fileNames, String myProfile) {
		super();
		this.postId = postId;
		this.fileNames = fileNames;
		this.myProfile = myProfile;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
}
