package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}
