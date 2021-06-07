package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;

public interface IPostService {
	
	Post addNewPost(PostDTO postDTO);
	List<PostDTO> findMyPosts(String username);
}
