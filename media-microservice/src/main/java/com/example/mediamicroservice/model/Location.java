package com.example.mediamicroservice.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "street")
	private String street;

	@Column(name = "country")
	private String country;

	@Column(name = "objectName")
	private String objectName;
	
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Story> story;

	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Post> post;

	public Location() {

	}

	public Location(Long id, String city, String street, String country, String objectName, List<Story> story,
			List<Post> post) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.country = country;
		this.objectName = objectName;
		this.story = story;
		this.post = post;
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
