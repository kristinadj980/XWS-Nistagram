package com.nistagram.profileMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profileMicroservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
