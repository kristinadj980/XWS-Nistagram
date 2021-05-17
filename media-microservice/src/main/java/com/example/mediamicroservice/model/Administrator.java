package com.example.mediamicroservice.model;


import java.util.List;

public class Administrator extends Person{
	
	public List<InappropriateContent> inappropriateContent;

	public Administrator() {
		super();
	}

	public List<InappropriateContent> getInappropriateContent() {
		return inappropriateContent;
	}

	public void setInappropriateContent(List<InappropriateContent> inappropriateContent) {
		this.inappropriateContent = inappropriateContent;
	}
}
