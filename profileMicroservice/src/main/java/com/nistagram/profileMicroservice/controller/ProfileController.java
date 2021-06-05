package com.nistagram.profileMicroservice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.profileMicroservice.dto.EditProfileDTO;
import com.nistagram.profileMicroservice.model.Gender;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.repository.ProfileRepository;
import com.nistagram.profileMicroservice.service.implService.PersonService;
import com.nistagram.profileMicroservice.service.implService.ProfileService;




@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {
	
	@Autowired
	private final ProfileService profileService;
	

	
	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	@GetMapping("/account")
	@PreAuthorize("hasRole('REGISTRED_USER')")
	public ResponseEntity<EditProfileDTO> getMyAccount() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		Person person = (Person) currentUser.getPrincipal();
		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
		Profile profile = profileService.findById(person.getId());
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		System.out.println(profile.getEmail());
		EditProfileDTO editProfileDTO = new EditProfileDTO(profile.getUsername(), profile.getName(), profile.getSurname(), profile.getEmail(), profile.getPhoneNumber(),
				profile.getBirthDate(), profile.getGender(), profile.getWebsite(), profile.getBiography());
		System.out.println(editProfileDTO.getEmail());
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

}
