package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;

public interface IProfileService {
	Profile findByEmail(String email);
	Profile save(PersonRequestDTO userRequest);
}
