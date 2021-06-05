package com.example.mediamicroservice.model;

import javax.persistence.*;

@Entity
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
    private String name;
	
	@Column(name = "fileName")
	private String fileName;

	public Media() {

	}

	public Media(Long id, String name, String fileName) {
		super();
		this.id = id;
		this.name = name;
		this.fileName = fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}