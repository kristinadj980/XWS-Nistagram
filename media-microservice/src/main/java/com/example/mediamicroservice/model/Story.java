package com.example.mediamicroservice.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "startTime", nullable = true)
	private LocalDateTime startTime;

	@Column(name = "endTime", nullable = true)
	private LocalDateTime endTime;

	@Column(name = "visible", nullable = true)
	private boolean visible;

	@Column(name = "date", nullable = true)
	private LocalDate date;
	
	@Column(name = "highlighted", nullable = true)
	private boolean highlighted;

	@Column(name = "closeFriends", nullable = true)
	private boolean closeFriends;
	
	@Column(name = "numberOfInappContent", nullable = true)
	private int numberOfInappropriateContent;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "story_medias",
	joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "medias_id", referencedColumnName = "id"))
	public List<Media> medias;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "story_tags",
	joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	public List<Tag> tags;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Location location;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "post_profile_tags",
	joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "profile_tags_id", referencedColumnName = "id"))
	private List<ProfileMedia> profileTags;
	   

	public Story() {

	}

	public Story(Long id, String description, LocalDateTime startTime, LocalDateTime endTime, boolean visible,
			LocalDate date, boolean highlighted, boolean closeFriends, int numberOfInappropriateContent,
			List<Media> medias, List<Tag> tags, Location location, List<ProfileMedia> profileTags) {
		super();
		this.id = id;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.visible = visible;
		this.date = date;
		this.highlighted = highlighted;
		this.closeFriends = closeFriends;
		this.numberOfInappropriateContent = numberOfInappropriateContent;
		this.medias = medias;
		this.tags = tags;
		this.location = location;
		this.profileTags = profileTags;
	}

	public boolean isCloseFriends() {
		return closeFriends;
	}

	public void setCloseFriends(boolean closeFriends) {
		this.closeFriends = closeFriends;
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



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime (LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public boolean isHighlighted() {
		return highlighted;
	}



	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}



	public int getNumberOfInappropriateContent() {
		return numberOfInappropriateContent;
	}



	public void setNumberOfInappropriateContent(int numberOfInappropriateContent) {
		this.numberOfInappropriateContent = numberOfInappropriateContent;
	}



	public List<Media> getMedia() {
		return medias;
	}



	public void setMedia(List<Media> media) {
		this.medias = media;
	}



	public List<Tag> getTag() {
		return tags;
	}



	public void setTag(List<Tag> tag) {
		this.tags = tag;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
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

	public List<ProfileMedia> getProfileTags() {
		return profileTags;
	}

	public void setProfileTags(List<ProfileMedia> profileTags) {
		this.profileTags = profileTags;
	}  
}
