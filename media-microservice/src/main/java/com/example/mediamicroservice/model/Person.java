package com.example.mediamicroservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.*;
import javax.persistence.InheritanceType;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {
	@Id
	@SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "Surname", nullable = false)
	private String surname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "birthDate", nullable = false)
	private Date birthDate;

	@Enumerated(EnumType.ORDINAL)
	private Gender gender;



	public Person(Long id, String username, String name, String surname, String email, String password, String role,
			String phoneNumber, Date birthDate, Gender gender) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	public Person() {
		
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}


}