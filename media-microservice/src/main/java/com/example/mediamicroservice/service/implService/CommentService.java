package com.example.mediamicroservice.service.implService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.repository.CommentRepository;
import com.example.mediamicroservice.service.ICommentService;

@Service
public class CommentService implements ICommentService{

	private final CommentRepository commentRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	@Override
	public Comment findById(Long id) {
		return commentRepository.findById(id).get();
	}
	
	public Comment answerOnComment(CommentDTO commentDTO) {
		Comment comment = findById(commentDTO.getId());
		if(comment.isAnswered() == true) {
			throw new IllegalArgumentException("You have already answered on this comment!");
		}else {
	    comment.setAnswer(commentDTO.getAnswer());
		comment.setAnswered(true);
		}
    	return commentRepository.save(comment);
    }

}
