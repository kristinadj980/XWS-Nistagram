package com.example.mediamicroservice.dto;

public class LikeDislikeHistoryDTO {
	private String usernemeLike;
	private String usernameDislike;
	private Long postId;
	
	public LikeDislikeHistoryDTO() {
		super();
	}

	public LikeDislikeHistoryDTO(String usernemeLike, String usernameDislike, Long postId) {
		super();
		this.usernemeLike = usernemeLike;
		this.usernameDislike = usernameDislike;
		this.postId = postId;
	}

	public String getUsernemeLike() {
		return usernemeLike;
	}

	public void setUsernemeLike(String usernemeLike) {
		this.usernemeLike = usernemeLike;
	}

	public String getUsernameDislike() {
		return usernameDislike;
	}

	public void setUsernameDislike(String usernameDislike) {
		this.usernameDislike = usernameDislike;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
}
