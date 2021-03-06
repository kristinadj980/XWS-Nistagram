package com.nistagram.profileMicroservice.dto;

import java.time.LocalDate;

import com.nistagram.profileMicroservice.model.Gender;

public class PersonRequestDTO {
	private String email;

    private String password;
    
    private String confirmPassword;
    
    private String username;

    private String name;

    private String surname;

    private String phoneNumber;
    
    private LocalDate birthDate;
    
    private Gender gender;

	public PersonRequestDTO() {
		super();
	}

	public PersonRequestDTO(String email, String password, String confirmPassword, String username, String name,
			String surname, String phoneNumber, LocalDate birthDate, Gender gender) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
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

}
