package com.nistagram.profileMicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.connections.MediaConnection;
import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.dto.FollowingDTO;
import com.nistagram.profileMicroservice.dto.PostDTO;
import com.nistagram.profileMicroservice.dto.RegistredUserDTO;
import com.nistagram.profileMicroservice.dto.VerificationRequestDTO;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.service.implService.ProfileService;


@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {
	
	private final ProfileService profileService;
	private final MediaConnection mediaConnection;
	
	@Autowired
	public ProfileController(ProfileService profileServie,MediaConnection mediaConnection) {
		this.profileService = profileServie;
		this.mediaConnection = mediaConnection;
	}

	@PostMapping("/proba")
	public ResponseEntity<?> proba()
	{
		System.out.println("Probaaaaaaaaaaaaaa");
		Profile pr=profileService.findById(102L);
		String username=pr.getUsername();
		
		System.out.println("Probaaaaaaaaaaaaaa"+username);
		return ResponseEntity.ok(username);
	}
	
	@GetMapping("/account")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<EditProfileDTO> getMyAccount() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile profile = profileService.findById(person.getId());
		EditProfileDTO editProfileDTO = new EditProfileDTO(profile.getUsername(), profile.getName(), profile.getSurname(), profile.getEmail(), profile.getPhoneNumber(),
				profile.getBirthDate(), profile.getGender(), profile.getWebsite(), profile.getBiography(), profile.getProfileStatus());
		
		return (ResponseEntity<EditProfileDTO>) (profile == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(editProfileDTO));

	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<String> updateProfileInfo(@RequestBody EditProfileDTO editProfileDTO) {
		try {
			profileService.update(editProfileDTO);
			return new ResponseEntity<>("Profile successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/updatePassword")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<String> updatePassword(@RequestBody EditProfileDTO editProfileDTO) {
		try {
			profileService.updatePassword(editProfileDTO);
			return new ResponseEntity<>("Password successfully updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/updateUsername")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<Boolean> updateUsername(@RequestBody EditProfileDTO editProfileDTO) {
		try {
			return new ResponseEntity<>(profileService.updateUsername(editProfileDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/loggedUserInfo")
	@PreAuthorize("hasRole('REGISTRED_USER')")  
	public ResponseEntity<RegistredUserDTO> getLoggedUserInfo() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile user = profileService.findById(person.getId());
		RegistredUserDTO userInfo = new RegistredUserDTO(user.getId(), user.getUsername());
		
		return userInfo == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(userInfo);
	}
	
	@GetMapping("/findPostsByUsername")
	@PreAuthorize("hasRole('REGISTRED_USER')")  
	public ResponseEntity<List<PostDTO>> getPostsInfo() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Profile user = profileService.findById(person.getId());
		try {
			ResponseEntity<List<PostDTO>> postDTOs = mediaConnection.getMyPosts(user.getUsername());
			System.out.println(postDTOs.getStatusCode());
			return postDTOs ;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Profile>> getAllUsers() {	
		List<Profile> usersProfiles = profileService.findAll();
		
		return (ResponseEntity<List<Profile>>) (usersProfiles == null
				? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(usersProfiles));
	}
	
	@GetMapping("/getUserProfile/{username}")
	public ResponseEntity getUserProfile(@PathVariable String username) {
		
		return new ResponseEntity(profileService.findByUsername(username), HttpStatus.OK); 
	}
	
	@PostMapping("/updateProfileStatus")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity updateProfileStatus(@RequestBody EditProfileDTO editProfileDTO) {
		try {
			profileService.updateProfileStatus(editProfileDTO.getUsername());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getFriendStatus/{username}")
	public ResponseEntity getFriendStatus(@PathVariable String username) {
		System.out.print("U kontroleru jeeeeeeeeeeeeeeeeeeeeee");
		return new ResponseEntity(profileService.getFriendStatus(username), HttpStatus.OK); 
	}
	
	@GetMapping("/getFollowingUsers")
	@PreAuthorize("hasRole('REGISTRED_USER')")  
	public ResponseEntity<List<FollowingDTO>> getFollowingUsers() {
		try {
			return new ResponseEntity<>(profileService.getFollowingUsers(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/verificationRequest")
	//@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity sendRequest(@RequestBody VerificationRequestDTO verificationRequestDTO){
		try {
			return new ResponseEntity<>(profileService.sendRequest(verificationRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
