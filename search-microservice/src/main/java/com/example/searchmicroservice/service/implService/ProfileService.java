package com.example.searchmicroservice.service.implService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.searchmicroservice.connection.ProfileConnection;
import com.example.searchmicroservice.repository.ProfileRepository;
import com.example.searchmicroservice.service.IProfileService;

@Service
public class ProfileService implements IProfileService{

	@Autowired
	private ProfileConnection profileConnection;
	private final ProfileRepository profileRepository;

	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}

	@Override
	public List<String> searchForProfile(String data) {
		List<String> publicProfiles = profileConnection.getPublicProfiles();
		List<String> searchResult = new ArrayList<String>();
		
		for(String p:publicProfiles) {
			try {
			if(p.equals(data))
				searchResult.add(p);
			else if(p.substring(0, data.length()).equals(data))
				searchResult.add(p);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return searchResult;
	}

}
