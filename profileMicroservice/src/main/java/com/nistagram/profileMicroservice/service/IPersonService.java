package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.model.Person;

public interface IPersonService {
	Person findByEmail(String email);
	Person findById(Long id);
}
