package com.nistagram.profileMicroservice.model;

import java.util.List;

public class Administrator extends Person {

	private Long id;
	private List<VerificationRequest> verificationRequests;

	public Administrator() {
		super();
	}

	public Administrator(List<VerificationRequest> verificationRequests, Long id) {
		super();
		this.verificationRequests = verificationRequests;
		this.id = id;
	}

	public List<VerificationRequest> getVerificationRequests() {
		return verificationRequests;
	}

	public void setVerificationRequests(List<VerificationRequest> verificationRequests) {
		this.verificationRequests = verificationRequests;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
