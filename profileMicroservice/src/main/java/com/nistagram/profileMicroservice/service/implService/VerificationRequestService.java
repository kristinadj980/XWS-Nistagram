package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.model.VerificationRequest;
import com.nistagram.profileMicroservice.repository.VerificationRequestrepository;
import com.nistagram.profileMicroservice.service.IVerificationRequestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationRequestService implements IVerificationRequestService {
	
	private final VerificationRequestrepository verificationRequestRepository;
	
	@Autowired
	public VerificationRequestService(VerificationRequestrepository verificationRequestRepository) {
		this.verificationRequestRepository = verificationRequestRepository;
	}
	
	public List<VerificationRequest> getVerificationRequests() {
		List<VerificationRequest> requests = verificationRequestRepository.findAll();
		System.out.println("*********************");
		System.out.println(requests.size());
		return requests;
	}
}
