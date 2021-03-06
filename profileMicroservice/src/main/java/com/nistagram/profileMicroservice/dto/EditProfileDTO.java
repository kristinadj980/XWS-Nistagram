package com.nistagram.profileMicroservice.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.nistagram.profileMicroservice.model.FriendRequestStatus;
import com.nistagram.profileMicroservice.model.Gender;
import com.nistagram.profileMicroservice.model.ProfileStatus;
import com.nistagram.profileMicroservice.model.VerificationCategory;

import lombok.Data;
@Data
public class EditProfileDTO {
	
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phoneNumber;
	private LocalDate birthDate;
	private Gender gender;
	private String website;
	private String biography;
	private String confirmPassword;
	private ProfileStatus profileStatus;
	private FriendRequestStatus friendRequestStatus;
	private String currentUsername;
	private String newUsername;
	private Boolean allowedTags; 
	private Boolean allowedMessages;  
	private Boolean verificated;
	private VerificationCategory category;
	
	public EditProfileDTO() {
		super();
	}
	
	public EditProfileDTO(String username, String name, String surname, String email, String password,
			String phoneNumber, LocalDate birthDate, Gender gender, String website, String biography,
			String confirmPassword, ProfileStatus profileStatus, FriendRequestStatus friendRequestStatus,
			String currentUsername, String newUsername, Boolean allowedTags, Boolean allowedMessages,Boolean verificated) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
		this.website = website;
		this.biography = biography;
		this.confirmPassword = confirmPassword;
		this.profileStatus = profileStatus;
		this.friendRequestStatus = friendRequestStatus;
		this.currentUsername = currentUsername;
		this.newUsername = newUsername;
		this.allowedTags = allowedTags;
		this.allowedMessages = allowedMessages;
		this.verificated = verificated;
	}
	public EditProfileDTO(String username, String name, String surname, String email, String phoneNumber,
			LocalDate birthDate, Gender gender, String website, String biography,ProfileStatus profileStatus,Boolean allowedTags, 
			Boolean allowedMessages,Boolean verificated, VerificationCategory category) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
		this.website = website;
		this.biography = biography;
		this.profileStatus = profileStatus;
		this.allowedTags = allowedTags;
		this.allowedMessages = allowedMessages;
		this.verificated = verificated;
		this.category = category;
	}

	public EditProfileDTO(String username, String name, String surname, String email, String password,
			String phoneNumber, LocalDate birthDate, Gender gender, String website, String biography,
			String confirmPassword, ProfileStatus profileStatus) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
		this.website = website;
		this.biography = biography;
		this.confirmPassword = confirmPassword;
		this.profileStatus = profileStatus;
	}

	public EditProfileDTO(String username, String name, String surname, String email, String phoneNumber,
			LocalDate birthDate, Gender gender, String website, String biography,ProfileStatus profileStatus) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
		this.website = website;
		this.biography = biography;
		this.profileStatus = profileStatus;
	}
	

	public EditProfileDTO(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ProfileStatus getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(ProfileStatus profileStatus) {
		this.profileStatus = profileStatus;
	}

	public FriendRequestStatus getFriendRequestStatus() {
		return friendRequestStatus;
	}

	public void setFriendRequestStatus(FriendRequestStatus friendRequestStatus) {
		this.friendRequestStatus = friendRequestStatus;
	}

	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public Boolean getAllowedTags() {
		return allowedTags;
	}

	public void setAllowedTags(Boolean allowedTags) {
		this.allowedTags = allowedTags;
	}

	public Boolean getAllowedMessages() {
		return allowedMessages;
	}

	public void setAllowedMessages(Boolean allowedMessages) {
		this.allowedMessages = allowedMessages;
	}

	public Boolean getVerificated() {
		return verificated;
	}

	public void setVerificated(Boolean verificated) {
		this.verificated = verificated;
	}

	public VerificationCategory getCategory() {
		return category;
	}

	public void setCategory(VerificationCategory category) {
		this.category = category;
	}

}
