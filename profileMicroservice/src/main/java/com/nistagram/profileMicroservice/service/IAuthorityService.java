package com.nistagram.profileMicroservice.service;

import com.nistagram.profileMicroservice.model.Authority;

public interface IAuthorityService {
	Authority findByname(String name);

}
