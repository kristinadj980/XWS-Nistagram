package com.example.mediamicroservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	public ProfileMedia registredUserProfile;
	
	@Column(name = "date")
	private LocalDateTime  date;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "isAnswered")
	private boolean isAnswered;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "comment_profile_tags",
	joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "profile_tags_id", referencedColumnName = "id"))
	private List<ProfileMedia> profileTags;
	   
	public Comment() {
		super();
	}

	public Comment(long id, String description, ProfileMedia registredUserProfile, LocalDateTime date, String answer,
			boolean isAnswered, List<ProfileMedia> profileTags) {
		super();
		this.id = id;
		this.description = description;
		this.registredUserProfile = registredUserProfile;
		this.date = date;
		this.answer = answer;
		this.isAnswered = isAnswered;
		this.profileTags = profileTags;
	}

	public Comment(String description, ProfileMedia registredUserProfile,LocalDateTime date,List<ProfileMedia> profileTags) {
		super();
		this.description = description;
		this.registredUserProfile = registredUserProfile;
		this.date = date;
		this.profileTags = profileTags;
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

	public ProfileMedia getRegistredUserProfile() {
		return registredUserProfile;
	}

	public void setRegistredUserProfile(ProfileMedia registredUserProfile) {
		this.registredUserProfile = registredUserProfile;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public List<ProfileMedia> getProfileTags() {
		return profileTags;
	}

	public void setProfileTags(List<ProfileMedia> profileTags) {
		this.profileTags = profileTags;
	}
}
