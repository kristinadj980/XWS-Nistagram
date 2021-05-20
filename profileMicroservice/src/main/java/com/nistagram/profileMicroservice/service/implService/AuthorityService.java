package com.nistagram.profileMicroservice.service.implService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nistagram.profileMicroservice.model.Authority;
import com.nistagram.profileMicroservice.repository.AuthorityRepository;
import com.nistagram.profileMicroservice.service.IAuthorityService;


@Service
public class AuthorityService implements IAuthorityService{
	 @Autowired
	    private AuthorityRepository authorityRepository;

	    @Override
	    public Authority findById(Long id) {
	        Authority auth = this.authorityRepository.getOne(id);

	        return auth;
	    }

	    @Override
	    public Authority findByname(String name) {
	        Authority auth = this.authorityRepository.findByName(name);
	        return auth;
	    }

}
