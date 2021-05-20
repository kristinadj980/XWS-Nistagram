package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.model.Authority;

public interface IAuthorityService {
	 Authority findById(Long id);
	 Authority findByname(String name);
	

}
