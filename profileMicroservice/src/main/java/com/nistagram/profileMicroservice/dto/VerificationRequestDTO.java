package com.nistagram.profileMicroservice.dto;

import com.nistagram.profileMicroservice.model.VerificationCategory;

public class VerificationRequestDTO {
	private String name;
	private String surname;
	private VerificationCategory verificationCategory;
	private String fileName;
	private String username;
	
	public VerificationRequestDTO() {
		super();
	}

	public VerificationRequestDTO(String name, String surname, VerificationCategory verificationCategory,
			String fileName, String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.verificationCategory = verificationCategory;
		this.fileName = fileName;
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

}
