package com.example.mediamicroservice.service;

import com.example.mediamicroservice.model.Comment;

public interface ICommentService {
	Comment findById(Long id);

}
