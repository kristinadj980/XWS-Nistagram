package com.example.mediamicroservice.dto;

public class CollectionDTO {
	
	private Long postId;
	private String myProfile;
	private String collectionName;
	private Long selectedCollection;
	
	public CollectionDTO() {
		super();
	}

	public CollectionDTO(Long postId, String myProfile, String collectionName, Long selectedCollection) {
		super();
		this.postId = postId;
		this.myProfile = myProfile;
		this.collectionName = collectionName;
		this.selectedCollection = selectedCollection;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public Long getSelectedCollection() {
		return selectedCollection;
	}

	public void setSelectedCollection(Long selectedCollection) {
		this.selectedCollection = selectedCollection;
	}

}
