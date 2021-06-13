package com.example.mediamicroservice.dto;

public class LikePostDTO {
	private String usernameTo;  //kome lajkujem
	private String fileName;
	private String usernameFrom;  //kolajkuje
	private int likes;
	private String comment;
	private int numberOfComments;
	private Long commentId;
	private String answer;
	
	public LikePostDTO() {
		super();
	}
	
	public LikePostDTO(String usernameTo, String fileName, String usernameFrom, int likes, String comment,
			int numberOfComments, Long commentId, String answer) {
		super();
		this.usernameTo = usernameTo;
		this.fileName = fileName;
		this.usernameFrom = usernameFrom;
		this.likes = likes;
		this.comment = comment;
		this.numberOfComments = numberOfComments;
		this.commentId = commentId;
		this.answer = answer;
	}

	public LikePostDTO(String usernameFrom, String comment,String answer, int numberOfComments,Long commentId) {
		super();
		this.usernameFrom = usernameFrom;
		this.comment = comment;
		this.answer = answer;
		this.numberOfComments = numberOfComments;
		this.commentId = commentId;
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
}
