package com.nistagram.profileMicroservice.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class VerificationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Enumerated(EnumType.ORDINAL)
	private VerificationCategory category;
	
	@Enumerated(EnumType.ORDINAL)
	private RequestStatus requestStatus;
	
	@OneToMany(mappedBy = "verificationRequest", fetch = FetchType.LAZY)
	private List<Profile> profiles;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Media media;
	
	public VerificationRequest() {
		super();
	}

	public VerificationRequest(Long id, String name, String surname, VerificationCategory category,
			RequestStatus requestStatus, List<Profile> profiles, Media media) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.category = category;
		this.requestStatus = requestStatus;
		this.profiles = profiles;
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

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}
