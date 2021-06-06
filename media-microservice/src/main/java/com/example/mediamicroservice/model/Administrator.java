package com.example.mediamicroservice.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends Person{

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "administrator_inappropriateContent",
    joinColumns = @JoinColumn(name = "administrator_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "inappropriateContent_id", referencedColumnName = "id"))
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
