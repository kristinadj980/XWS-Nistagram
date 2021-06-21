package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.dto.NotificationDTO;
import com.nistagram.profileMicroservice.model.FriendNotifications;
import com.nistagram.profileMicroservice.model.Notification;
import com.nistagram.profileMicroservice.model.NotificationDescription;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.repository.NotificationRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.INotificationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
	
	private final NotificationRepository notificationRepository;
	private final ProfileService profileService;
	private final ProfileRepository profileRepository;
	
	@Autowired
	public NotificationService(NotificationRepository notificationRepository, ProfileService profileService,
			 ProfileRepository profileRepository) {
		super();
		this.notificationRepository = notificationRepository;
		this.profileService = profileService;
		this.profileRepository = profileRepository;
	}

	@Override
	public void postNotify(String username) {
		Profile profile = profileService.findByUsername(username);
		List<Profile> followers = profile.getFollowers();
		Notification notification = new Notification();
		notification.setDate(LocalDate.now());
		notification.setNotificationDescription(NotificationDescription.POST);
		notification.setProfile(profile);
		notificationRepository.save(notification);
		for(Profile f:followers) {
			Profile p = profileService.findByUsername(f.getUsername());
			List<FriendNotifications> friendNotifications = p.getFriendNotifications();
			for(FriendNotifications fn:friendNotifications) {
				if(fn.getProfile().getUsername().equals(username)) {
					if(fn.getPost() == true) {
						if(p.getNotifications().isEmpty()) {
							List<Notification> notifications = new ArrayList<>();
							notifications.add(notification);
							p.setNotifications(notifications);
							profileRepository.save(p);
						}else {
							p.getNotifications().add(notification);
							profileRepository.save(p);
						}
					}
				}
			}
		}
		
	}

	@Override
	public List<NotificationDTO> getNotifications() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  profileService.findById(person.getId());
		List<Notification> notifications = profile.getNotifications();
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		
		for(Notification n:notifications) {
			notificationDTOs.add(new NotificationDTO(n.getId(), n.getNotificationDescription(), 
					n.getNotificationDescription().getNotificationDescription(), n.getDate(), n.getProfile().getUsername()));
		}
		
		return notificationDTOs;
	}

	@Override
	public void storyNotify(String username) {
		System.out.println("**********SERVIS*************");
		Profile profile = profileService.findByUsername(username);
		List<Profile> followers = profile.getFollowers();
		Notification notification = new Notification();
		notification.setDate(LocalDate.now());
		notification.setNotificationDescription(NotificationDescription.STORY);
		notification.setProfile(profile);
		notificationRepository.save(notification);
		for(Profile f:followers) {
			Profile p = profileService.findByUsername(f.getUsername());
			List<FriendNotifications> friendNotifications = p.getFriendNotifications();
			for(FriendNotifications fn:friendNotifications) {
				if(fn.getProfile().getUsername().equals(username)) {
					if(fn.getStory() == true) {
						if(p.getNotifications().isEmpty()) {
							List<Notification> notifications = new ArrayList<>();
							notifications.add(notification);
							p.setNotifications(notifications);
							profileRepository.save(p);
						}else {
							p.getNotifications().add(notification);
							profileRepository.save(p);
						}
					}
				}
			}
		}
		
	}
	
	
}
