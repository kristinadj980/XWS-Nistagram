package com.nistagram.profileMicroservice.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class VerificationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "username", nullable = false)
	private String username;

	@Enumerated(EnumType.ORDINAL)
	private VerificationCategory category;
	
	@Enumerated(EnumType.ORDINAL)
	private RequestStatus requestStatus;
	
	/*@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "verification_request_profiles",
	joinColumns = @JoinColumn(name = "verification_request_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
	private List<Profile> profiles;*/
	
	@OneToOne(cascade = CascadeType.ALL)
	private Media media;
	
	public VerificationRequest() {
		super();
	}

	public VerificationRequest(Long id, String name, String surname, String username, VerificationCategory category,
			RequestStatus requestStatus, Media media) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.category = category;
		this.requestStatus = requestStatus;
		this.media = media;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public VerificationCategory getCategory() {
		return category;
	}

	public void setCategory(VerificationCategory category) {
		this.category = category;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
