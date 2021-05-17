package com.nistagram.profileMicroservice.model;

import javax.persistence.*;

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
	
	public VerificationRequest() {
		super();
	}

	public VerificationRequest(Long id, String name, String surname, VerificationCategory category) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.category = category;
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
	
	

}
