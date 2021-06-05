package com.example.mediamicroservice.service.implService;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.service.IPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	
	
	private final PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public PostDTO addNewPost(PostDTO postDTO) {

		return null;
	}
	
}
