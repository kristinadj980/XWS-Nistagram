package com.example.mediamicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	Optional<Comment> findById(Long id);

}
