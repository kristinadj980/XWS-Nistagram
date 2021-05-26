package com.example.mediamicroservice.model;

public class Comment {
	   private long id;
	   private String description;
	   public Profile registredUserProfile;
	   
	   
		public Comment(long id, String description, Profile registredUserProfile) {
		super();
		this.id = id;
		this.description = description;
		this.registredUserProfile = registredUserProfile;
	}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Profile getRegistredUserProfile() {
			return registredUserProfile;
		}
		public void setRegistredUserProfile(Profile registredUserProfile) {
			this.registredUserProfile = registredUserProfile;
		}
}
