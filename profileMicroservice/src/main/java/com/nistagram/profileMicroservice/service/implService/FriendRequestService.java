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
import com.nistagram.profileMicroservice.service.IFriendRequestService;

@Service
public class FriendRequestService implements IFriendRequestService{
	
	private final FriendRequestRepository friendRequestRepository;
	private final ProfileService profileService;
	
	@Autowired
	public FriendRequestService(FriendRequestRepository friendRequestRepository, ProfileService profileService) {
		super();
		this.friendRequestRepository = friendRequestRepository;
		this.profileService = profileService;
	}

	@Override
	public void newFriendRequest(FriendRequestDTO friendRequestDTO) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile userSender = profileService.findById(person.getId());
		Profile userReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());
		List<Profile> folowers = new ArrayList<Profile>();
		FriendRequest friendRequest = new FriendRequest();


		if(userReceiver.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			userSender.getFollowing().add(userReceiver);
			userReceiver.getFollowers().add(userSender);
		}else if(userSender.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			friendRequest.setFriendRequestStatus(FriendRequestStatus.waiting);
			friendRequest.setProfile(userSender);
			userReceiver.getFriendRequests().add(friendRequest);
		}else {
			//both private
		}
		
		
	}
	
}




