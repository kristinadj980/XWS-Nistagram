package com.nistagram.profileMicroservice.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Person {

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "administrator_verificationRequests",
    joinColumns = @JoinColumn(name = "administrator_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "verificationRequests_id", referencedColumnName = "id"))
	private List<VerificationRequest> verificationRequests;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "administrator_agentRequests",
    joinColumns = @JoinColumn(name = "administrator_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "agentRequests_id", referencedColumnName = "id"))
	private List<Profile> agentRequests;

	public Administrator() {
		super();
	}

	
	public Administrator(List<VerificationRequest> verificationRequests, List<Profile> agentRequests) {
		super();
		this.verificationRequests = verificationRequests;
		this.agentRequests = agentRequests;
	}


	public List<VerificationRequest> getVerificationRequests() {
		return verificationRequests;
	}

	public void setVerificationRequests(List<VerificationRequest> verificationRequests) {
		this.verificationRequests = verificationRequests;
	}


	public List<Profile> getAgentRequests() {
		return agentRequests;
	}


	public void setAgentRequests(List<Profile> agentRequests) {
		this.agentRequests = agentRequests;
	}

}
