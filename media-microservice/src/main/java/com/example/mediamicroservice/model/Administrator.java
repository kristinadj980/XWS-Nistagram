package com.example.mediamicroservice.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Person{

	@ManyToMany(targetEntity = InappropriateContent.class,  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
