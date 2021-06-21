package com.nistagram.profileMicroservice.dto;

public class CommentNotifyDTO {
	private String commentTo;
	private String commentFrom;
	
	
	public CommentNotifyDTO() {
		super();
	}

	public CommentNotifyDTO(String commentTo, String commentFrom) {
		super();
		this.commentTo = commentTo;
		this.commentFrom = commentFrom;
	}

	public String getCommentTo() {
		return commentTo;
	}

	public void setCommentTo(String commentTo) {
		this.commentTo = commentTo;
	}

	public String getCommentFrom() {
		return commentFrom;
	}

	public void setCommentFrom(String commentFrom) {
		this.commentFrom = commentFrom;
	}
	
}
