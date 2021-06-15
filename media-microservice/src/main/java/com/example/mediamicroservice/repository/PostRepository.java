package com.example.mediamicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findById(Long id);
}
