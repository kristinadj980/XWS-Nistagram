package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Tag{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="name")
	private String name;
	
	public Tag() {
		
	}
	
	public Tag(String name) {
		this.name=name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
}