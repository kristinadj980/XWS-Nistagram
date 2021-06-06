package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long>{
}
