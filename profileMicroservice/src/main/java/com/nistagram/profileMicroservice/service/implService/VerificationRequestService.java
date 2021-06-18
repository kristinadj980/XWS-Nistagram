package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.RequestStatus;
import com.nistagram.profileMicroservice.model.VerificationRequest;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.repository.VerificationRequestrepository;
import com.nistagram.profileMicroservice.service.IVerificationRequestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationRequestService implements IVerificationRequestService {
	
	private final VerificationRequestrepository verificationRequestRepository;
	private final ProfileService profileService;
	private final ProfileRepository profileRepository;
	
	@Autowired
	public VerificationRequestService(VerificationRequestrepository verificationRequestRepository,ProfileService profileService,ProfileRepository profileRepository) {
		this.verificationRequestRepository = verificationRequestRepository;
		this.profileService = profileService;
		this.profileRepository = profileRepository;
	}
	
	@Override
	public List<VerificationRequestDTO> getVerificationRequests() {
		List<VerificationRequest> requests = verificationRequestRepository.findAll();
		List<VerificationRequestDTO> requestDTOs = new ArrayList<VerificationRequestDTO>();
		for (VerificationRequest v : requests) {
			if(v.getRequestStatus().equals(RequestStatus.sent)) {
			requestDTOs.add(new VerificationRequestDTO(v.getName(), v.getSurname(), v.getCategory(), v.getMedia().getFileName(),v.getUsername(), v.getId(), v.getRequestStatus()));
		}
		}
		
		return requestDTOs;
	}
	
	@Override
	public VerificationRequest acceptRequest(VerificationRequestDTO requestDTO) {
		Profile profile = profileService.findByUsername(requestDTO.getUsername());
		if(profile == null)
			throw new IllegalArgumentException("Profile doesn't exist!");
		VerificationRequest request = profile.getVerificationRequest();
		if(request == null) {
			throw new IllegalArgumentException("This profile doesn't have a verification request!");
		}
		if(request.getId() == requestDTO.getId()) {
			request.setRequestStatus(RequestStatus.accepted);
			profile.setVerificationRequest(request);
			profileRepository.save(profile);
		}
		
		
		return request;
	}
	
	@Override
	public VerificationRequest rejectRequest(VerificationRequestDTO requestDTO) {
		Profile profile = profileService.findByUsername(requestDTO.getUsername());
		if(profile == null)
			throw new IllegalArgumentException("Profile doesn't exist!");
		VerificationRequest request = profile.getVerificationRequest();
		if(request == null) {
			throw new IllegalArgumentException("This profile doesn't have a verification request!");
		}
		if(request.getId() == requestDTO.getId()) {
			request.setRequestStatus(RequestStatus.rejected);
			profile.setVerificationRequest(request);
			profileRepository.save(profile);
		}
		
		return request;
	}
}
