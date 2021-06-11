package com.example.mediamicroservice.dto;

public class LikePostDTO {
	private String usernameTo;  //kome lajkujem
	private String fileName;
	private String usernameFrom;  //kolajkuje
	private int likes;
	
	public LikePostDTO() {
		super();
	}
	
	public LikePostDTO(String usernameTo, String fileName, String usernameFrom, int likes) {
		super();
		this.usernameTo = usernameTo;
		this.fileName = fileName;
		this.usernameFrom = usernameFrom;
		this.likes = likes;
	}

	public String getUsernameTo() {
		return usernameTo;
	}

	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUsernameFrom() {
		return usernameFrom;
	}

	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}