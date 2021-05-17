package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Post {
	
	private String description;
	private	List<Profile> like;
	private List<Profile> dislike;
	private String comment;
	private Date date;
	
	
	public Post(String description, List<Profile> like, List<Profile> dislike, String comment, Date date) {
		super();
		this.description = description;
		this.like = like;
		this.dislike = dislike;
		this.comment = comment;
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Profile> getLike() {
		return like;
	}


	public void setLike(List<Profile> like) {
		this.like = like;
	}


	public List<Profile> getDislike() {
		return dislike;
	}


	public void setDislike(List<Profile> dislike) {
		this.dislike = dislike;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
}