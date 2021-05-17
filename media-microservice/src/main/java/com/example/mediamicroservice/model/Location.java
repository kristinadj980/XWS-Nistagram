package com.example.mediamicroservice.model;

import java.util.List;

public class Location {
	  
	   private String city;
	   private String street;
	   private String streetNumber;
	   private String country;
	   
	   public List<Story> story;
	   public List<Post> post;
	   
	   
	   public Location(String city, String street, String streetNumber, String country, List<Story> story,
				List<Post> post) {
			super();
			this.city = city;
			this.street = street;
			this.streetNumber = streetNumber;
			this.country = country;
			this.story = story;
			this.post = post;
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


	public List<Story> getStory() {
		return story;
	}


	public void setStory(List<Story> story) {
		this.story = story;
	}


	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}
	   
}
