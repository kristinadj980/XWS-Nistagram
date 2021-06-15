package com.example.mediamicroservice.dto;

import java.util.List;

public class CollectionInfoDTO {
	
	private List<PostDTO> posts;
	private String name;
	private Long id;
	
	public CollectionInfoDTO() {
		super();
	}

	public CollectionInfoDTO(List<PostDTO> posts, String name, Long id) {
		super();
		this.posts = posts;
		this.name = name;
		this.id = id;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
