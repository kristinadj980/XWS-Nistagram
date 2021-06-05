package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.PostDTO;

public interface IPostService {
	
	PostDTO addNewPost(PostDTO postDTO);
}
