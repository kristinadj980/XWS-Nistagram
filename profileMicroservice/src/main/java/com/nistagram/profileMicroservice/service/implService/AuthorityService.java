package com.nistagram.profileMicroservice.service.implService;

import org.springframework.stereotype.Service;

import com.nistagram.profileMicroservice.model.Authority;
import com.nistagram.profileMicroservice.repository.AuthorityRepository;
import com.nistagram.profileMicroservice.service.IAuthorityService;


@Service
public class AuthorityService implements IAuthorityService {
	
	private final AuthorityRepository authorityRepository;
	
    public AuthorityService(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Authority findByname(String name) {
		Authority auth = this.authorityRepository.findByName(name);
        return auth;
	}

}
