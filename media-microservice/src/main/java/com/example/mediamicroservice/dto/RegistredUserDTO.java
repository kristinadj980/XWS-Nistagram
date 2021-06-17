package com.example.mediamicroservice.dto;

public class RegistredUserDTO {
	private Long id;
	private String username;
	
	public RegistredUserDTO() {
		super();
	}

	public RegistredUserDTO(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
