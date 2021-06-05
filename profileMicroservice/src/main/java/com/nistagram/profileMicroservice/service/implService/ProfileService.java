package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.model.Authority;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.repository.AuthorityRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.IProfileService;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {
	
	private final ProfileRepository profileRepository;
	private final  PasswordEncoder passwordEncoder;
	private final AuthorityService authService;
	private final AuthorityRepository authorityRepository;
	
	@Autowired
	public ProfileService(ProfileRepository profileRepository,PasswordEncoder passwordEncoder,AuthorityService authService,AuthorityRepository authorityRepository) {
		this.profileRepository = profileRepository;
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Profile findByEmail(String email) {
		return profileRepository.findByEmail(email);
	}
	
	@Override
	public Profile save(PersonRequestDTO userRequest) {
		Profile profile =  new Profile();
        profile.setName(userRequest.getName());
        profile.setSurname(userRequest.getSurname());
        profile.setEmail(userRequest.getEmail());
        profile.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        profile.setPhoneNumber(userRequest.getPhoneNumber());
        profile.setGender(userRequest.getGender());
        profile.setUsername(userRequest.getUsername());
        profile.setBirthDate(userRequest.getBirthDate());
        Authority authority = authService.findByname("ROLE_REGISTRED_USER");
        List<Authority> auth = new ArrayList<Authority>();
        if(authority==null) {
            authorityRepository.save(new Authority("ROLE_REGISTRED_USER"));
            auth.add(authService.findByname("ROLE_REGISTRED_USER"));
        }
        else {
            auth.add(authority);
        }
        profile.setAuthorities(auth);
        profile.setEnabled(true);
        try {
        	profileRepository.save(profile);
        	System.out.println("SACUVAOOOO");
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
        return profile;
	}

	
	@Override
	public Profile findById(Long id) {
		return profileRepository.findById(id).orElseGet(null);
		
	}

	@Override
	public void update(EditProfileDTO editProfileDTO) {
		Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		profile.setName(editProfileDTO.getName());
		profile.setSurname(editProfileDTO.getSurname());
		profile.setBiography(editProfileDTO.getBiography());
		profile.setBirthDate(editProfileDTO.getBirthDate());
		profile.setEmail(editProfileDTO.getEmail());
		profile.setGender(editProfileDTO.getGender());
		profile.setUsername(editProfileDTO.getUsername());
		profile.setWebsite(editProfileDTO.getWebsite());
		profile.setPhoneNumber(editProfileDTO.getPhoneNumber());

	    profileRepository.save(profile);
	}
}
