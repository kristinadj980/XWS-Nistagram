package com.example.demo.model;


import java.util.List;

import javax.persistence.*;


@Entity
public class Location{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "streetNumber", nullable = false)
	private String streetNumber;

	@Column(name = "country", nullable = false)
	private String country;

	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Post> post;
	   
	   
	   public Location(String city, String street, String streetNumber, String country, List<Post> post) {
			super();
			this.city = city;
			this.street = street;
			this.streetNumber = streetNumber;
			this.country = country;
			this.post = post;
		}

	public Location() {

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


	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}
	
	
}