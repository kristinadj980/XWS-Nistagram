package com.nistagram.profileMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
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
}
