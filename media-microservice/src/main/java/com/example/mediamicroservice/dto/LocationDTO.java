package com.example.mediamicroservice.dto;


public class LocationDTO {
	
	private String city;
	private String street;
	private String streetNumber;
	private String country;
	
	public LocationDTO() {
		super();
	}

	public LocationDTO(String city, String street, String streetNumber, String country) {
		super();
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.country = country;
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
		this.country = country;
	}
	
	
}
