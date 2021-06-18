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
	Boolean updateUsername(EditProfileDTO editProfileDTO);
	List<Profile> findAll();
	void updateProfileStatus(String username);
	List<FollowingDTO> getFollowingUsers();
	FriendRequestStatus getFriendStatus(String username);
	Boolean updateMessageAllowance(Boolean messageAllowance);
	Boolean updateTagAllowance(Boolean tagAllowance);
	List<String> getMuted();
	List<String> getNotMuted();
	List<String> muteFriend(EditProfileDTO editProfileDTO);
	void unmuteFriend(EditProfileDTO editProfileDTO);
}
