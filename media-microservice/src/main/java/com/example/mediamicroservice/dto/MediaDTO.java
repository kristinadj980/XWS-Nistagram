package com.example.mediamicroservice.dto;

public class MediaDTO {
	
	private String name;
	private String fileName;

	public MediaDTO() {
	}
	
	public MediaDTO(String name, String fileName) {
		super();
		this.name = name;
		this.fileName = fileName;
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
