package com.example.mediamicroservice.dto;


public class LocationDTO {
	
	private Long id;
	private String city;
	private String street;
	private String country;
	private String objectName;
	
	public LocationDTO() {
		super();
	}

	public LocationDTO(Long id, String city, String street, String country, String objectName) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.country = country;
		this.objectName = objectName;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	
	
}
