package com.nistagram.profileMicroservice.service.implService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nistagram.profileMicroservice.dto.FriendNotificationsDTO;
import com.nistagram.profileMicroservice.model.FriendNotifications;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.repository.FriendNotificationsRepository;
import com.nistagram.profileMicroservice.service.IFriendNotificationsService;

@Service
public class FriendNotificationsService implements IFriendNotificationsService {

	private final FriendNotificationsRepository friendNotificationsRepository;
	private final ProfileService profileService;
	
	@Autowired
	public FriendNotificationsService(FriendNotificationsRepository friendNotificationsRepository, ProfileService profileService) {
		super();
		this.friendNotificationsRepository = friendNotificationsRepository;
		this.profileService = profileService;
	}


	@Override
	public FriendNotificationsDTO getfriendNotifications(String username) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  profileService.findById(person.getId());
		List<FriendNotifications> notifications =  profile.getFriendNotifications();
		FriendNotificationsDTO notificationDTO = new FriendNotificationsDTO();
		
		
		for(FriendNotifications f:notifications)
			if(f.getProfile().getUsername().equals(username))
				notificationDTO = new FriendNotificationsDTO(f.getId(), f.getMessage(), f.getPost(), f.getStory(), f.getComment());
			
		return notificationDTO;
		
	}


	@Override
	public FriendNotificationsDTO  changeNotificationStatus(FriendNotificationsDTO friendNotificationsDTO) {
		FriendNotifications frNotifications = friendNotificationsRepository.findById(friendNotificationsDTO.getId()).get();
		Boolean current;
		if(friendNotificationsDTO.getMessage() != null) {
			current = frNotifications.getMessage();
			frNotifications.setMessage(!current);
		}else if(friendNotificationsDTO.getPost() != null) {
			current = frNotifications.getPost();
			frNotifications.setPost(!current);
		}else if(friendNotificationsDTO.getStory() != null) {
			current = frNotifications.getStory();
			frNotifications.setStory(!current);
		}else {
			current = frNotifications.getComment();
			frNotifications.setComment(!current);
		}
		
		friendNotificationsRepository.save(frNotifications);
		FriendNotificationsDTO response  = new FriendNotificationsDTO(frNotifications.getId(), frNotifications.getMessage(), frNotifications.getPost(),
				frNotifications.getStory(), frNotifications.getComment());
		
		
		return response;
	}

}










