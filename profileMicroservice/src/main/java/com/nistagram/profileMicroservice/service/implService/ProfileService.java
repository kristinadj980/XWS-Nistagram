package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.FollowingDTO;
import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.model.Authority;
import com.nistagram.profileMicroservice.model.FriendRequest;
import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.repository.AuthorityRepository;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.IProfileService;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
        profile.setProfileStatus(ProfileStatus.publicProfile);
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
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser = findById(person.getId());
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee");
		Profile searchedUser = findByUsername(username);
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee"  + username);
		List<Profile> following = logedUser.getFollowing();
		FriendRequestStatus status  = FriendRequestStatus.notFriends;
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee" + status);
		for(Profile p: following)
			if(p.getUsername().equals(username))
				return FriendRequestStatus.friends;
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee");
		List<FriendRequest> requests = searchedUser.getFriendRequests();
		for(FriendRequest r:requests)
			if(r.getProfile().getUsername().equals(logedUser.getUsername()))
				status = r.getFriendRequestStatus();
		
			
		return status;
	}

	@Override
	public List<FollowingDTO> getFollowers(String username) {
		System.out.println(username);
		//Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		//Person person = (Person) currentUser.getPrincipal();
		Profile user = findByUsername(username);
		System.out.println(user.getUsername());
		System.out.println("**********************");
		List<Profile> followers = user.getFollowers();
		List<FollowingDTO> followingDTO = new ArrayList<FollowingDTO>();
		
		for(Profile p: followers) {
			followingDTO.add(new FollowingDTO(p.getUsername()));
		
			System.out.println(p.getUsername());
		}
		return followingDTO;
	}

	@Override
	public void addCloseFriend(String username) {
		// TODO Auto-generated method stub
		System.out.println(username);
		System.out.println("U SERVISU SAM");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser = findById(person.getId());
		System.out.println(logedUser.getName());
		
		Profile closeFriend=findByUsername(username.substring(0, username.length()-1));
		System.out.println(closeFriend.getName());
		
		List<Profile> closedFriends=logedUser.getCloseFriends();
		/*for(Profile p:closedFriends) {
			if(p.getUsername().equals(username))
				System.out.println("Already close friend");
		}*/
		closedFriends.add(closeFriend);
		profileRepository.save(logedUser);
	}

	@Override
	public Boolean closeFriends(List<Profile> profiles) {
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser = findById(person.getId());
		
		System.out.print("U servisu jeeeeeeeeeeeeeeeeeeeeee");
		
		List<Profile> closeFriends = logedUser.getCloseFriends();
		
		for(Profile p: closeFriends)
			for(Profile p1:profiles) {
				if(p.getUsername().equals(p1))
					return true;
			}
			
		return false;	}

	@Override
	public void deleteCloseFriend(String username) {
		System.out.println(username);
		System.out.println("U SERVISU SAM");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser = findById(person.getId());
		System.out.println(logedUser.getName());
		
		Profile closeFriend=findByUsername(username.substring(0, username.length()-1));
		System.out.println(closeFriend.getName());
		
		List<Profile> closedFriends=logedUser.getCloseFriends();
		closedFriends.remove(closeFriend);
		profileRepository.save(logedUser);
		
	}
}
