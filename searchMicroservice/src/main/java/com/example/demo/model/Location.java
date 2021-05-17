package com.example.demo.model;


import javax.persistence.*;

@Entity
public class Location{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "street")
	private String street;

	@Column(name = "streetNumber")
	private String streetNumber;

	@Column(name = "country")
	private String country;

	
	public Location() {
		
	}
	
	public Location(String city, String street, String streetNumber, String country) {
		super();
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		country = country;
	}
	
	
}