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
	
	@Autowired
	private MediaConnection mediaConnection;
	
	@Autowired
	public ProfileService(ProfileRepository profileRepository,PasswordEncoder passwordEncoder,AuthorityService authService,
			AuthorityRepository authorityRepository) {
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
		Profile profile =  findById(person.getId());
		List<Profile> following = profile.getFollowing();
		List<FollowingDTO> followingDTO = new ArrayList<FollowingDTO>();
		
		for(Profile p: following)
			followingDTO.add(new FollowingDTO(p.getUsername()));
		
		
		return followingDTO;
	}

	@Override
	public FriendRequestStatus getFriendStatus(String username) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile logedUser =  findById(person.getId());
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

	@Override
	public void blockUser(String username) {
		// TODO Auto-generated method stub
				System.out.println(username);
				System.out.println("U SERVISU SAM");
				Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
				Person person = (Person) currentUser.getPrincipal();
				Profile logedUser = findById(person.getId());
				System.out.println(logedUser.getName());
				
				Profile blockFriend=findByUsername(username.substring(0, username.length()-1));
				System.out.println(blockFriend.getName());
				
				List<Profile> blockedFriends=logedUser.getBlockedUsers();
				
				blockedFriends.add(blockFriend);
				
				System.out.println("BRISE BLOKIRANOG IZ PRATIOCA");
				logedUser.getFollowing().remove(blockFriend);
				logedUser.getFollowers().remove(blockFriend);

				System.out.println("BRISE BLOKIRANOG IZ PRATIOCA");
				blockFriend.getFollowing().remove(logedUser);
				blockFriend.getFollowers().remove(logedUser);
				
				profileRepository.save(logedUser);
				profileRepository.save(blockFriend);
	}
	
	@Override
	public Boolean updateUsername(EditProfileDTO editProfileDTO) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
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
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		profile.setAllowedMessages(!messageAllowance);

		profileRepository.save(profile);
		return !messageAllowance;
	}

	@Override
	public Boolean updateTagAllowance(Boolean tagAllowance) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		profile.setAllowedTags(!tagAllowance);
		profileRepository.save(profile);
		return !tagAllowance;

	}


	@Override
	public List<String> getMuted() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		List<Profile> mutedFriends = profile.getMutedFriends();
		List<String> mutedUsernames = new ArrayList<String>();
		if(mutedFriends == null) 
			return mutedUsernames;
		else
			for(Profile m:mutedFriends)
				mutedUsernames.add(m.getUsername());
		
		return mutedUsernames;
	}

	@Override
	public List<String> muteFriend(EditProfileDTO editProfileDTO) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		Profile profileToMute = profileRepository.findByUsername(editProfileDTO.getUsername());
		List<Profile> pokusaj = new ArrayList<Profile>();
		if(profile.getMutedFriends() == null) {
			pokusaj.add(profileToMute);
			profile.setMutedFriends(pokusaj);
		}else {
			List<Profile> mutedFriends = profile.getMutedFriends();
			mutedFriends.add(profileToMute);
			profile.setMutedFriends(mutedFriends);
		}
		profileRepository.save(profile);
		List<String> mutedUsernames = new ArrayList<>();
		try {
		mutedUsernames =  getMuted();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return mutedUsernames;
	}

	@Override
	public List<String> getNotMuted() {
		List<FollowingDTO> following = getFollowingUsers();
		List<String> muted = getMuted();
		List<String> notMuted = new ArrayList<>();
		
		if(muted.isEmpty())
			for(FollowingDTO f:following)
				notMuted.add(f.getFollowing());
		else if(following.isEmpty() && muted.isEmpty())
			return notMuted;
		else if(following.isEmpty() && !muted.isEmpty())
			return muted;
		else
			for(FollowingDTO f:following)
				for(String m:muted)
					if(!m.equals(f.getFollowing())) 
						notMuted.add(f.getFollowing());
		
		return notMuted;
	}

	@Override
	public void unmuteFriend(EditProfileDTO editProfileDTO) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		Profile profileToUnmute = profileRepository.findByUsername(editProfileDTO.getUsername());

		profile.getMutedFriends().remove(profileToUnmute);
		profileRepository.save(profile);
		
	}

	@Override
	public void unblockUser(String username) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<FollowingDTO> getFollowers() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile =  findById(person.getId());
		List<Profile> followers = profile.getFollowers();
		List<FollowingDTO> followingDTO = new ArrayList<FollowingDTO>();
		
		for(Profile p: followers)
			followingDTO.add(new FollowingDTO(p.getUsername()));
		
		
		return followingDTO;
	}
	
}
