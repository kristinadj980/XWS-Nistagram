package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.connections.MediaConnection;
import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.EditUsernameDTO;
import com.nistagram.profileMicroservice.dto.FollowingDTO;
import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.model.Authority;
import com.nistagram.profileMicroservice.model.FriendRequest;
import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Media;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.model.RequestStatus;
import com.nistagram.profileMicroservice.model.VerificationRequest;
import com.nistagram.profileMicroservice.repository.AuthorityRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.repository.VerificationRequestrepository;
import com.nistagram.profileMicroservice.service.IProfileService;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {
	
	private final ProfileRepository profileRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthorityService authService;
	private final AuthorityRepository authorityRepository;
	private final VerificationRequestrepository verificationRequestRepository;
	@Autowired
	private MediaConnection mediaConnection;
	
	@Autowired
	public ProfileService(ProfileRepository profileRepository,PasswordEncoder passwordEncoder,AuthorityService authService,
			AuthorityRepository authorityRepository,VerificationRequestrepository verificationRequestRepository) {
		this.profileRepository = profileRepository;
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
		this.authorityRepository = authorityRepository;
		this.verificationRequestRepository = verificationRequestRepository;
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
        profile.setProfileStatus(ProfileStatus.publicProfile);
        profile.setAllowedTags(false);
        profile.setAllowedMessages(false);
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

	private Boolean checkUsername(String username) {
		List<String> allUsernames = profileRepository.findAllUsernames();
		
		for(String u:allUsernames)
			if(u.equals(username))
				return false;
		
		return true;
	}
	
	@Override
	public Profile findById(Long id) {
		return profileRepository.findById(id).get();
		
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

	@Override
	public void updatePassword(EditProfileDTO editProfileDTO) {
		Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		profile.setPassword(passwordEncoder.encode(editProfileDTO.getConfirmPassword()));
		
		profileRepository.save(profile);
		
	}

	@Override
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	@Override
	public Profile findByUsername(String username) {
		return profileRepository.findByUsername(username);
	}

	@Override
	public void updateProfileStatus(String username) {
		Profile profile = findByUsername(username);
		if(profile.getProfileStatus().equals(ProfileStatus.privateProfile))
			profile.setProfileStatus(ProfileStatus.publicProfile);
		else
			profile.setProfileStatus(ProfileStatus.privateProfile);
	
		profileRepository.save(profile);
	}

	@Override
	public List<FollowingDTO> getFollowingUsers() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile user = findById(person.getId());
		List<Profile> following = user.getFollowing();
		List<FollowingDTO> followingDTO = new ArrayList<FollowingDTO>();
		
		for(Profile p: following)
			followingDTO.add(new FollowingDTO(p.getUsername()));
		
		
		return followingDTO;
	}

	@Override
	public FriendRequestStatus getFriendStatus(String username) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser = findById(person.getId());
		Profile searchedUser = findByUsername(username);
		List<Profile> following = logedUser.getFollowing();
		FriendRequestStatus status  = FriendRequestStatus.notFriends;
		for(Profile p: following)
			if(p.getUsername().equals(username))
				return FriendRequestStatus.friends;
		List<FriendRequest> requests = searchedUser.getFriendRequests();
		for(FriendRequest r:requests)
			if(r.getProfile().getUsername().equals(logedUser.getUsername()))
				status = r.getFriendRequestStatus();
		
			
		return status;
	}

	@Override
	public Boolean updateUsername(EditProfileDTO editProfileDTO) {
		Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		checkUsername(editProfileDTO.getNewUsername());
		if(checkUsername(editProfileDTO.getCurrentUsername()) == false && checkUsername(editProfileDTO.getNewUsername()) == true) {
			profile.setUsername(editProfileDTO.getNewUsername());
			profileRepository.save(profile);
			mediaConnection.changeUsername(new EditUsernameDTO(editProfileDTO.getCurrentUsername(), editProfileDTO.getNewUsername()));
			System.out.println("Proslo");
			return true;
		}
		return false;
	}

	@Override
	public Profile sendRequest(VerificationRequestDTO verificationRequestDTO ) {
		VerificationRequest request = new VerificationRequest();
		Profile myProfile = profileRepository.findByUsername(verificationRequestDTO.getUsername());
		Media media = mediaConnection.saveVerificationDocument(verificationRequestDTO.getFileName());
		request.setCategory(verificationRequestDTO.getVerificationCategory());
		request.setMedia(media);
		request.setRequestStatus(RequestStatus.sent);
		request.setName(verificationRequestDTO.getName());
		request.setSurname(verificationRequestDTO.getSurname());
		request.setUsername(verificationRequestDTO.getUsername());
		myProfile.setVerificationRequest(request);
		
		return profileRepository.save(myProfile);
	}

	@Override
	public Boolean updateMessageAllowance(Boolean messageAllowance) {
		Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		profile.setAllowedMessages(!messageAllowance);

		profileRepository.save(profile);
		return !messageAllowance;
	}

	@Override
	public Boolean updateTagAllowance(Boolean tagAllowance) {
		Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		profile.setAllowedTags(!tagAllowance);
		profileRepository.save(profile);
		return !tagAllowance;

	}
}
