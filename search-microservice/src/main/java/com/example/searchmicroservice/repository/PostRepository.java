package com.example.searchmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchmicroservice.model.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
