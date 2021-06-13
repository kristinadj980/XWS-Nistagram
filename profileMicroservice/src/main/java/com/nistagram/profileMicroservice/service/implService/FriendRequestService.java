package com.nistagram.profileMicroservice.service.implService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nistagram.profileMicroservice.dto.FriendRequestDTO;
import com.nistagram.profileMicroservice.model.FriendRequest;
import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.repository.FriendRequestRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.IFriendRequestService;

@Service
public class FriendRequestService implements IFriendRequestService{
	
	private final FriendRequestRepository friendRequestRepository;
	private final ProfileService profileService;
	private final ProfileRepository profileRepository;
	
	@Autowired
	public FriendRequestService(FriendRequestRepository friendRequestRepository, ProfileService profileService,
			ProfileRepository profileRepository) {
		super();
		this.friendRequestRepository = friendRequestRepository;
		this.profileService = profileService;
		this.profileRepository = profileRepository;
	}

	private Profile getLogedUser() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile = profileService.findById(person.getId());
		return profile;
	}
	
	@Override
	public String newFriendRequest(FriendRequestDTO friendRequestDTO) {
		Profile userSender = getLogedUser();
		Profile userReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());
		List<Profile> folowers = new ArrayList<Profile>();
		FriendRequest friendRequest = new FriendRequest();

		if(userReceiver.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			userSender.getFollowing().add(userReceiver);
			userReceiver.getFollowers().add(userSender);
			profileRepository.save(userSender);
			profileRepository.save(userReceiver);
			return "You are friends!";
		}else {
			friendRequest.setFriendRequestStatus(FriendRequestStatus.waiting);
			friendRequest.setProfile(userSender);
			userReceiver.getFriendRequests().add(friendRequest);
			friendRequestRepository.save(friendRequest);
			profileRepository.save(userReceiver);
			return "Request is sent!";
		}
	}

	@Override
	public List<FriendRequestDTO> getMyRequests() {
		Profile profile = getLogedUser();
		List<FriendRequest> requests = profile.getFriendRequests();
		
		List<FriendRequestDTO> requestDTOs = new ArrayList<FriendRequestDTO>();
		for(FriendRequest f:requests)
			if(f.getFriendRequestStatus().equals(FriendRequestStatus.waiting))
				requestDTOs.add(new FriendRequestDTO(f.getProfile().getUsername(),f.getId()));
		
		
		return requestDTOs;
	}


	@Override
	public String setRequestResponse(FriendRequestDTO friendRequestDTO) {
		FriendRequest request = friendRequestRepository.findById(friendRequestDTO.getRequestId()).get();
		Profile requestSender = request.getProfile();
		Profile requestReceiver  = getLogedUser();
		
		if(friendRequestDTO.getRequestStatus().equals(FriendRequestStatus.approved))
		{
			requestSender.getFollowing().add(requestReceiver);
			requestReceiver.getFollowers().add(requestSender);
			profileRepository.save(requestReceiver);
			profileRepository.save(requestSender);
			request.setFriendRequestStatus(FriendRequestStatus.approved);
		}else
			request.setFriendRequestStatus(FriendRequestStatus.rejected);
		
		friendRequestRepository.save(request);
		
		return "Request is updated";
	}


	@Override
	public void cancelRequest(FriendRequestDTO friendRequestDTO) {
		Profile requestSender = getLogedUser();
		Profile requestReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());
		List<FriendRequest> requests = requestReceiver.getFriendRequests();
		
		for(FriendRequest f:requests) 
			if(f.getProfile().getUsername().equals(requestSender.getUsername())) {
				f.setFriendRequestStatus(FriendRequestStatus.notFriends);
				friendRequestRepository.save(f);
			}
			
	
	}
	
}




