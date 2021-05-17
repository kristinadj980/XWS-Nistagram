package com.example.mediamicroservice.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Post{
	 private String description;
	   private List<Profile> like;
	   private List<Profile> dislike;
	   private String comment;
	   private java.util.Date date;
	   
	   public java.util.Collection<Media> media;
	   public Location location;
	   public Tag[] tag;
	   
	   public Post(String description, List<Profile> like, List<Profile> dislike, String comment, Date date,
			Collection<Media> media, Location location, Tag[] tag) {
			super();
			this.description = description;
			this.like = like;
			this.dislike = dislike;
			this.comment = comment;
			this.date = date;
			this.media = media;
			this.location = location;
			this.tag = tag;
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

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public java.util.Collection<Media> getMedia() {
		return media;
	}

	public void setMedia(java.util.Collection<Media> media) {
		this.media = media;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Tag[] getTag() {
		return tag;
	}

	public void setTag(Tag[] tag) {
		this.tag = tag;
	}
	   
	   
}