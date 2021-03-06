package com.nistagram.profileMicroservice.service.implService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nistagram.profileMicroservice.dto.FriendRequestDTO;
import com.nistagram.profileMicroservice.model.FriendNotifications;
import com.nistagram.profileMicroservice.model.FriendRequest;
import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.repository.FriendNotificationsRepository;
import com.nistagram.profileMicroservice.repository.FriendRequestRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.IFriendRequestService;

@Service
public class FriendRequestService implements IFriendRequestService{
	
	private final FriendRequestRepository friendRequestRepository;
	private final ProfileService profileService;
	private final ProfileRepository profileRepository;
	private final FriendNotificationsRepository friendNotificationsRepository;
	
	@Autowired
	public FriendRequestService(FriendRequestRepository friendRequestRepository, ProfileService profileService,
			ProfileRepository profileRepository, FriendNotificationsRepository friendNotificationsRepository) {
		super();
		this.friendRequestRepository = friendRequestRepository;
		this.profileService = profileService;
		this.profileRepository = profileRepository;
		this.friendNotificationsRepository = friendNotificationsRepository;
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
		FriendRequest friendRequest = new FriendRequest();
		FriendNotifications friendNotifications = new FriendNotifications(false, false, false, false, userReceiver);
		List<FriendNotifications> f = new ArrayList<>();
		f.add(friendNotifications);
		if(userReceiver.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			userSender.getFollowing().add(userReceiver);
			userReceiver.getFollowers().add(userSender);
			friendNotificationsRepository.save(friendNotifications);
			System.out.println("hoce li ovo puci ");
			userSender.setFriendNotifications(f);
			System.out.println("hoce li ovo puci ");
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

	@Override
	public void unfollowFriend(FriendRequestDTO friendRequestDTO) {
		Profile requestSender = getLogedUser();
		Profile requestReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());
		
		requestSender.getFollowing().remove(requestReceiver);
		requestReceiver.getFollowers().remove(requestSender);
		profileRepository.save(requestSender);
		profileRepository.save(requestReceiver);
		
		
		List<FriendRequest> requests = requestReceiver.getFriendRequests();
		
		for(FriendRequest f:requests) 
			if(f.getProfile().getUsername().equals(requestSender.getUsername())) {
				f.setFriendRequestStatus(FriendRequestStatus.notFriends);
				friendRequestRepository.save(f);
			}
		
		requestReceiver.getFriendRequests();
		
	}

	@Override
	public void removeFollowera(FriendRequestDTO friendRequestDTO) {
		Profile requestSender = getLogedUser();
		Profile requestReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());
		
		requestSender.getFollowers().remove(requestReceiver);
		requestReceiver.getFollowing().remove(requestSender);
		profileRepository.save(requestSender);
		profileRepository.save(requestReceiver);
		
		List<FriendRequest> requests = requestSender.getFriendRequests();
		
		for(FriendRequest f:requests) 
			if(f.getProfile().getUsername().equals(requestReceiver.getUsername())) {
				f.setFriendRequestStatus(FriendRequestStatus.notFriends);
				friendRequestRepository.save(f);
			}
	}
	
}




