package com.nistagram.profileMicroservice.dto;

import com.nistagram.profileMicroservice.model.RequestStatus;
import com.nistagram.profileMicroservice.model.VerificationCategory;

public class VerificationRequestDTO {
	private String name;
	private String surname;
	private VerificationCategory verificationCategory;
	private String fileName;
	private String username;
	private Long id;
	private RequestStatus requestStatus;
	
	public VerificationRequestDTO() {
		super();
	}
	
	public VerificationRequestDTO(String name, String surname, VerificationCategory verificationCategory,
			String fileName, String username, Long id, RequestStatus requestStatus) {
		super();
		this.name = name;
		this.surname = surname;
		this.verificationCategory = verificationCategory;
		this.fileName = fileName;
		this.username = username;
		this.id = id;
		this.requestStatus = requestStatus;
	}
	
	public VerificationRequestDTO(VerificationCategory verificationCategory, String username) {
		super();
		this.verificationCategory = verificationCategory;
		this.username = username;
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

	public VerificationCategory getVerificationCategory() {
		return verificationCategory;
	}

	public void setVerificationCategory(VerificationCategory verificationCategory) {
		this.verificationCategory = verificationCategory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

}
