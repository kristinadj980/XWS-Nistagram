package com.example.mediamicroservice.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post{

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   @Column(name = "description")
	   private String description;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "post_likes",
	   joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	   inverseJoinColumns = @JoinColumn(name = "profile_who_liked_id", referencedColumnName = "id"))
	   private List<ProfileMedia> likes;
	   
	   @Column(name = "numberOfLikes")
	   private Integer numberOfLikes;
	   
	   @Column(name = "numberOfDislikes")
	   private Integer numberOfDisikes;
	   
	   @Column(name = "numberOfComments")
	   private Integer numberOfComments;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "post_dislikes",
	   joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	   inverseJoinColumns = @JoinColumn(name = "profile_who_disliked_id", referencedColumnName = "id"))
	   private List<ProfileMedia> dislikes;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinTable(name = "post_comments",
		joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
		private List<Comment> comments;

	   @Column(name = "date")
	   private LocalDateTime  date;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JoinTable(name = "post_media",
	   joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	   inverseJoinColumns = @JoinColumn(name = "media_id", referencedColumnName = "id"))
	   public List<Media> medias;

	   @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	    @JoinColumn(name="location_id")
		public Location location;

	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinTable(name = "post_tags",
		joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
		public List<Tag> tags;
	   
	
	public Post() {
		super();
	}

	
	public Post(Long id, String description, List<ProfileMedia> likes, List<ProfileMedia> dislikes, List<Comment> comments,
			LocalDateTime  date, List<Media> medias, Location location, List<Tag> tags) {
		super();
		this.id = id;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.comments = comments;
		this.date = date;
		this.medias = medias;
		this.location = location;
		this.tags = tags;
	}

	public Post(Long id, String description, List<ProfileMedia> likes, Integer numberOfLikes, Integer numberOfDisikes,Integer numberOfComments,
			List<ProfileMedia> dislikes, List<Comment> comments, LocalDateTime date, List<Media> medias,
			Location location, List<Tag> tags) {
		super();
		this.id = id;
		this.description = description;
		this.likes = likes;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDisikes = numberOfDisikes;
		this.numberOfComments = numberOfComments;
		this.dislikes = dislikes;
		this.comments = comments;
		this.date = date;
		this.medias = medias;
		this.location = location;
		this.tags = tags;
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

	public List<ProfileMedia> getLike() {
		return likes;
	}

	public void setLike(List<ProfileMedia> like) {
		this.likes = like;
	}

	public List<ProfileMedia> getDislike() {
		return dislikes;
	}

	public void setDislike(List<ProfileMedia> dislike) {
		this.dislikes = dislike;
	}

	
	public List<ProfileMedia> getLikes() {
		return likes;
	}


	public void setLikes(List<ProfileMedia> likes) {
		this.likes = likes;
	}


	public List<ProfileMedia> getDislikes() {
		return dislikes;
	}


	public void setDislikes(List<ProfileMedia> dislikes) {
		this.dislikes = dislikes;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Media> getMedias() {
		return medias;
	}


	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}


	public List<Tag> getTags() {
		return tags;
	}


	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	public LocalDateTime  getDate() {
		return date;
	}

	public void setDate(LocalDateTime  date) {
		this.date = date;
	}

	public List<Media> getMedia() {
		return medias;
	}

	public void setMedia(List<Media> media) {
		this.medias = media;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Tag> getTag() {
		return tags;
	}

	public void setTag(List<Tag> tag) {
		this.tags = tag;
	}

	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public Integer getNumberOfDisikes() {
		return numberOfDisikes;
	}

	public void setNumberOfDisikes(Integer numberOfDisikes) {
		this.numberOfDisikes = numberOfDisikes;
	}

	public Integer getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}
	
}