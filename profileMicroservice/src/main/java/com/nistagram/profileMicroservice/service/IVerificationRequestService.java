package com.nistagram.profileMicroservice.service;

import java.util.List;

import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.model.VerificationRequest;

public interface IVerificationRequestService {
	VerificationRequest acceptRequest(VerificationRequestDTO requestDTO);
	VerificationRequest rejectRequest(VerificationRequestDTO requestDTO);
	List<VerificationRequestDTO> getVerificationRequests();
}
