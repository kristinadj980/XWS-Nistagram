package com.example.mediamicroservice.model;

import javax.persistence.*;

@Entity
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

	@Column(name = "name", nullable = false, unique = true)
   private String name;

	public Media(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Media() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}