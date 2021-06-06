package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.ProfileMedia;

public interface IProfileMediaService {
	ProfileMedia findByUsername(String username);
	void addPostToProfile(PostDTO postDTO, Post post);
}
