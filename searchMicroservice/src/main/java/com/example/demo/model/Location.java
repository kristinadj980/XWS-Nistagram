package com.example.demo.model;



public class Location{
	
	private String city;
	private String street;
	private String streetNumber;
	private String Country;

	
	public Location() {
		
	}
	
	public Location(String city, String street, String streetNumber, String country) {
		super();
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		Country = country;
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
		return Country;
	}


	public void setCountry(String country) {
		Country = country;
	}
	
	
}