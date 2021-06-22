package com.example.mediamicroservice.dto;

import java.util.List;

public class LikePostDTO {
	private String usernameTo;  //kome lajkujem
	private String fileName;
	private String usernameFrom;  //kolajkuje
	private int likes;
	private String comment;
	private int numberOfComments;
	private Long commentId;
	private String answer;
	private Long postId;
	private List<ProfileDTO> taggedUsers;
	
	public LikePostDTO() {
		super();
	}

	public LikePostDTO(String usernameTo, String fileName, String usernameFrom, int likes, String comment,
			int numberOfComments, Long commentId, String answer, Long postId) {
		super();
		this.usernameTo = usernameTo;
		this.fileName = fileName;
		this.usernameFrom = usernameFrom;
		this.likes = likes;
		this.comment = comment;
		this.numberOfComments = numberOfComments;
		this.commentId = commentId;
		this.answer = answer;
		this.postId = postId;
	}

	public LikePostDTO(String usernameFrom, String comment,String answer, int numberOfComments,Long commentId, List<ProfileDTO> taggedUsers) {
		super();
		this.usernameFrom = usernameFrom;
		this.comment = comment;
		this.answer = answer;
		this.numberOfComments = numberOfComments;
		this.commentId = commentId;
		this.taggedUsers = taggedUsers;
	}

	public String getUsernameTo() {
		return usernameTo;
	}

	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUsernameFrom() {
		return usernameFrom;
	}

	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public List<ProfileDTO> getTaggedUsers() {
		return taggedUsers;
	}

	public void setTaggedUsers(List<ProfileDTO> taggedUsers) {
		this.taggedUsers = taggedUsers;
	}
}
