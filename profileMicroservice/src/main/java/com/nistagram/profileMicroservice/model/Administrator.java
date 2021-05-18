package com.nistagram.profileMicroservice.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Person {

	@ManyToMany(targetEntity = VerificationRequest.class,  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VerificationRequest> verificationRequests;

	public Administrator() {
		super();
	}

	public Administrator(List<VerificationRequest> verificationRequests) {
		super();
		this.verificationRequests = verificationRequests;
	}

	public List<VerificationRequest> getVerificationRequests() {
		return verificationRequests;
	}

	public void setVerificationRequests(List<VerificationRequest> verificationRequests) {
		this.verificationRequests = verificationRequests;
	}

}
