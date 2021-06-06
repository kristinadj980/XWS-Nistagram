package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
}
