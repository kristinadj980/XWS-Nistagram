package com.example.mediamicroservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post{

	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

	   @Column(name = "description", nullable = false)
	   private String description;

	   @ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	   private List<Profile> like;

	   @ManyToMany(targetEntity = Profile.class,  cascade = CascadeType.ALL)
	   private List<Profile> dislike;

	   @Column(name = "comment", nullable = false)
	   private String comment;

	   @Column(name = "date", nullable = false)
	   private Date date;
	   
	   public List<Media> media;
	   @Column(name = "location", nullable = false)
	   public Location location;
	   public Tag[] tag;
	   
	   public Post(Long id,String description, List<Profile> like, List<Profile> dislike, String comment, Date date,
			List<Media> media, Location location, Tag[] tag) {
			super();
			this.id=id;
			this.description = description;
			this.like = like;
			this.dislike = dislike;
			this.comment = comment;
			this.date = date;
			this.media = media;
			this.location = location;
			this.tag = tag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
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