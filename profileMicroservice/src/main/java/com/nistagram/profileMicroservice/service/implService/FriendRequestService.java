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

	@Override
	public void newFriendRequest(FriendRequestDTO friendRequestDTO) {

		System.out.println("****************************************SERVIS");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("****************************************SERVIS");
		Person person = (Person) currentUser.getPrincipal();

		System.out.println("****************************************SERVIS");
		Profile userSender = profileService.findById(person.getId());

		System.out.println("****************************************SERVIS");
		Profile userReceiver = profileService.findByUsername(friendRequestDTO.getUserReceiver());

		System.out.println("****************************************SERVIS");
		List<Profile> folowers = new ArrayList<Profile>();

		System.out.println("****************************************SERVIS");
		FriendRequest friendRequest = new FriendRequest();


		System.out.println("****************************************SERVIS");
		if(userReceiver.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			System.out.println("**************************************** zapratili smo javan profil");
			userSender.getFollowing().add(userReceiver);
			System.out.println("**************************************** dodali smo u following");
			userReceiver.getFollowers().add(userSender);
			System.out.println("**************************************** dodali smo u followers");
			profileRepository.save(userSender);
			profileRepository.save(userReceiver);
		}else if(userSender.getProfileStatus().equals(ProfileStatus.publicProfile)) {
			friendRequest.setFriendRequestStatus(FriendRequestStatus.waiting);
			friendRequest.setProfile(userSender);
			userReceiver.getFriendRequests().add(friendRequest);
		}else {
			//both private
		}
		
		
	}
	
}




