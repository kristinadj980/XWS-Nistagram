package com.nistagram.profileMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.FollowingDTO;
import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;

public interface IProfileService {
	Profile findByEmail(String email);
	Profile save(PersonRequestDTO userRequest);
	Profile findById(Long id);
	Profile findByUsername(String username);
	void update(EditProfileDTO editProfileDTO);
	void updatePassword(EditProfileDTO editProfileDTO);
	List<Profile> findAll();
	void updateProfileStatus(String username);
	List<FollowingDTO> getFollowingUsers();
	List<FollowingDTO> getFollowers(String username);
	FriendRequestStatus getFriendStatus(String username);
	void addCloseFriend(String username);
	void deleteCloseFriend(String username);
	Boolean closeFriends(List<Profile> profiles);
	void blockUser(String username);
	void unblockUser(String username);
}
